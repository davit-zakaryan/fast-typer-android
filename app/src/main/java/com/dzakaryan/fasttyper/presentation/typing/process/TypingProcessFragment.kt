package com.dzakaryan.fasttyper.presentation.typing.process

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Spannable
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.dzakaryan.fasttyper.presentation.core.hideSoftKeyboard
import com.dzakaryan.fasttyper.presentation.core.showSoftKeyboard
import kotlinx.android.synthetic.main.fragment_typing_process.*
import org.koin.android.viewmodel.ext.android.viewModel

class TypingProcessFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingProcessViewModel by viewModel()
    private val countDownTimer: CountDownTimer by lazy { initCountDownTimer() }
    private lateinit var styleSpan: StyleSpan
    private lateinit var spannableText: Spannable
    private var isMistakeShown: Boolean = false
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_typing_process

    override fun retrieveViewData(bundle: Bundle?) {
        viewModel.textToType = bundle?.getString(ARG_RANDOM_TEXT)
    }

    override fun initViews(view: View) {
        super.initViews(view)
        //textToType.setText(getString(R.string.text), TextView.BufferType.SPANNABLE)
        textToType.setText(viewModel.textToType, TextView.BufferType.SPANNABLE)
        textToType.movementMethod = ScrollingMovementMethod()


        val words = spannableText.split("\\s+".toRegex()).map { word ->
            word.replace("""^|$""".toRegex(), "")
        }
        words.forEach { println(it) }

        //val queue: Queue<String> = LinkedList()

        //initCountDownTimer()

        fakeEditText.showSoftKeyboard()

        fakeEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No need
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let {

                }
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let { editable ->
                    afterTextChanged(editable, words)
                }
            }

        })
    }

    override fun onDestroyView() {
        requireActivity().hideSoftKeyboard()
        super.onDestroyView()
    }
    //endregion

    //region Private utility methods
    private fun afterTextChanged(editable: Editable, words: List<String>) {
        if (editable.isEmpty()) {
            return
        }
        if (editable.last().isWhitespace()) {
            if (words[viewModel.currentWordIndex].contentEquals(editable.trim())) {
                viewModel.successTypedIndex = viewModel.successTypedIndex +
                        words[viewModel.currentWordIndex].length + 1 //plus space

                viewModel.currentWholeTypedIndex++
                viewModel.currentWordIndex++

                highlightSuccess(0, viewModel.successTypedIndex)
                fakeEditText.text.clear()
            } else {
                // mistake is typed
                highlightMistake(
                    viewModel.successTypedIndex + editable.length - 1,
                    viewModel.successTypedIndex + editable.length
                )
            }
        } else if (words[viewModel.currentWordIndex].startsWith(editable) &&
            words[viewModel.currentWordIndex].length >= editable.length
        ) {
            viewModel.currentWholeTypedIndex++
            //viewModel.successTypedIndex = viewModel.successTypedIndex + editable.length
            println("viewModel.successTypedIndex = " + viewModel.successTypedIndex + "length =" + editable.length)
            if (isMistakeShown) {
                unHightlightMistake(
                    viewModel.successTypedIndex,
                    viewModel.successTypedIndex + editable.length + 1
                )
            }
            highlightSuccess(
                viewModel.successTypedIndex,
                viewModel.successTypedIndex + editable.length
            )

        } else {
            highlightMistake(
                viewModel.successTypedIndex + editable.length - 1,
                viewModel.successTypedIndex + editable.length
            )
        }
    }

    private fun unHightlightMistake(start: Int, end: Int) {
        fakeEditText.setBackgroundResource(R.drawable.bg_input_primary)
        fakeEditText.setTextColor(Color.BLACK)
        spannableText.setSpan(
            ForegroundColorSpan(Color.BLACK),
            start,
            end,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    private fun highlightSuccess(start: Int, end: Int) {
        fakeEditText.setBackgroundResource(R.drawable.bg_input_primary)
        fakeEditText.setTextColor(Color.BLACK)
        spannableText.setSpan(
            ForegroundColorSpan(Color.GREEN),
            start,
            end,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )
        isMistakeShown = false
    }

    private fun highlightMistake(start: Int, end: Int) {
        if (!isMistakeShown) {
            fakeEditText.setBackgroundResource(R.drawable.bg_input_secondary)
            fakeEditText.setTextColor(Color.WHITE)
            spannableText.setSpan(
                ForegroundColorSpan(Color.RED),
                start,
                end,
                SPAN_EXCLUSIVE_EXCLUSIVE
            )
            isMistakeShown = true
        }

    }

    private fun initCountDownTimer(): CountDownTimer {
        return object : CountDownTimer(TYPING_DURATION_MILES, SECOND_IN_MILES) {
            override fun onTick(millisUntilFinished: Long) {
                chronometer.text = millisUntilFinished.toString()
                //http://metaphorpsum.com/paragraphs/3/10
            }

            override fun onFinish() {
                chronometer.text = "Time is up"
            }
        }
    }
    //endregion

    //region Companion object
    companion object {
        private const val SECOND_IN_MILES = 1000L
        private const val GAME_DURATION_SEC = 60
        const val TYPING_DURATION_MILES = GAME_DURATION_SEC * SECOND_IN_MILES //30sec
        const val ARG_RANDOM_TEXT = "arg.RANDOM_TEXT"
    }
    //endregion
}