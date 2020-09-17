package com.dzakaryan.fasttyper.presentation.typing.process

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.dzakaryan.fasttyper.presentation.core.showSoftKeyboard
import kotlinx.android.synthetic.main.fragment_typing_process.*
import org.koin.android.viewmodel.ext.android.viewModel


class TypingProcessFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingProcessViewModel by viewModel()
    private val countDownTimer: CountDownTimer by lazy { initCountDownTimer() }
    private lateinit var styleSpan: StyleSpan
    private lateinit var spannableText: Spannable
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_typing_process


    //endregion


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textToType.setText(getString(R.string.text), TextView.BufferType.SPANNABLE)
        spannableText = textToType.text as Spannable

        //initCountDownTimer()

        textToType.showSoftKeyboard()

//        fakeEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                //
//            }
//
//            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
//                text?.let {
//                    //textToType.text = it
//
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                //
//                s?.let {
//
//                    println(it.length)
//
//                    if (it.length - 1 >= 0)
//                        spannableText.setSpan(
//                            ForegroundColorSpan(Color.BLUE),
//                            it.length - 1,
//                            it.length - 1,
//                            SPAN_INCLUSIVE_INCLUSIVE
//                        )
//
//                    spannableText.setSpan(
//                        ForegroundColorSpan(Color.RED),
//                        it.length - 1,
//                        it.length,
//                        SPAN_COMPOSING
//                    )
//
////                    if(it.length > 4)
////                    it.setSpan(styleSpan, 0,it.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                }
//            }
//
//        })
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


    //region Companion object
    companion object {
        private const val SECOND_IN_MILES = 1000L
        private const val GAME_DURATION_SEC = 60
        const val TYPING_DURATION_MILES = GAME_DURATION_SEC * SECOND_IN_MILES //30sec
    }
    //endregion
}