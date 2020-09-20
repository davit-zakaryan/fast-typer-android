package com.dzakaryan.fasttyper.presentation.typing.process

import android.app.Application
import android.graphics.Color
import android.os.CountDownTimer
import android.text.Editable
import android.text.Spannable
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel

class TypingProcessViewModel(
    application: Application,

    ) : BaseViewModel(application) {

    //region Properties
    private val countDownTimer = createCountDownTimer()
    private var startTypingTimestamp = -1L
    private var typedCharactersCount = 0

    private lateinit var spannableTextToType: Spannable
    private lateinit var words: List<String>
    private var currentWordIndex = 0
    private var isMistakeHighlighted = false
    var lastWpm: String = ""
    var textToType: String? = null

    private val _timerValueLiveData = MutableLiveData<String>()
    val timerValueLiveData: LiveData<String> get() = _timerValueLiveData

    private val _wpmValueLiveData = MutableLiveData<String>()
    val wpmValueLiveData: LiveData<String> get() = _wpmValueLiveData

    private val _typingStatusLiveData = MutableLiveData<TypingStatus>()
    val typingStatusLiveData: LiveData<TypingStatus> get() = _typingStatusLiveData

    private val _clearTypedText = MutableLiveData<Boolean>()
    val clearTypedText: LiveData<Boolean> get() = _clearTypedText
    //endregion

    //region Public methods
    fun setSpannableText(spannable: Spannable) {
        this.spannableTextToType = spannable
        words = this.spannableTextToType.split("\\s+".toRegex()).map { word ->
            word.replace("""^|$""".toRegex(), "")
        }
    }

    fun startTypingProcess() {
        countDownTimer.start()
        startTypingTimestamp = System.currentTimeMillis()
    }

    fun afterTextChanged(editable: Editable) {
        if (editable.isEmpty()) {
            return
        }
        val currentWord = words[currentWordIndex]
        if (editable.last().isWhitespace()) {
            if (currentWord.contentEquals(editable.trim())) {
                // Typed word equals before space
                typedCharactersCount += currentWord.length + 1 //plus space
                currentWordIndex++

                highlightSuccess(0, typedCharactersCount)
                clearTypedText()
            } else {
                // Typed word has mistake before space
                val mistakeEndIndex = typedCharactersCount + editable.length
                highlightMistake(mistakeEndIndex - 1, mistakeEndIndex)
            }
        } else if (currentWord.startsWith(editable) && currentWord.length >= editable.length) {
            highlightSuccess(0, typedCharactersCount + editable.length)
        } else if (currentWord.startsWith(editable)) {
            //TODO check
            val mistakeEndIndex = typedCharactersCount + currentWord.length
            highlightMistake(mistakeEndIndex - 1, mistakeEndIndex)
        } else {
            if (!isMistakeHighlighted) {
                val mistakeEndIndex = typedCharactersCount + editable.length
                highlightMistake(mistakeEndIndex - 1, mistakeEndIndex)
                isMistakeHighlighted = true
            }
        }
    }

    private fun highlightMistake(start: Int, end: Int) {
        spannableTextToType.setSpan(
            ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    private fun clearTypedText() {
        _clearTypedText.value = true
    }

    private fun highlightSuccess(start: Int, end: Int) {
        spannableTextToType.setSpan(
            ForegroundColorSpan(Color.GREEN), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableTextToType.setSpan(
            ForegroundColorSpan(Color.BLACK),
            end,
            spannableTextToType.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        isMistakeHighlighted = false
    }
    //endregion

    //region Private utility methods
    private fun createCountDownTimer() =
        object : CountDownTimer(TYPING_DURATION_MILES, SECOND_IN_MILES) {
            override fun onTick(millisUntilFinished: Long) {
                onCountDownTick(millisUntilFinished)
            }

            override fun onFinish() {
                onCountDownFinished()
            }

        }

    private fun onCountDownFinished() {
        _timerValueLiveData.value = "Time is up"
        lastWpm = String.format("%.1f", calculateWpm())
        _typingStatusLiveData.value = TypingStatus.FINISHED
    }

    private fun onCountDownTick(millisUntilFinished: Long) {
        _timerValueLiveData.value = "${millisUntilFinished / 1000 + 1}"
        _wpmValueLiveData.value = String.format("%.1f", calculateWpm())
    }

    private fun calculateWpm(): Float {
        return (typedCharactersCount.toFloat() / 5f)
            .times(60f)
            .div((System.currentTimeMillis() - startTypingTimestamp) / 1000)
    }
    //endregion

    //region Override open methods
    override fun onCleared() {
        countDownTimer.cancel()
    }
    //endregion

    //region Companion object
    companion object {
        private const val SECOND_IN_MILES = 1000L
        private const val GAME_DURATION_SEC = 180
        const val TYPING_DURATION_MILES = GAME_DURATION_SEC * SECOND_IN_MILES //30sec
    }
    //endregion

    enum class TypingStatus {
        STARTED, FINISHED
    }
}