package com.dzakaryan.fasttyper.presentation.typing.process

import android.os.Bundle
import android.text.Spannable
import android.text.method.ScrollingMovementMethod
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.dzakaryan.fasttyper.presentation.core.afterTextChanged
import com.dzakaryan.fasttyper.presentation.core.hideSoftKeyboard
import com.dzakaryan.fasttyper.presentation.core.showSoftKeyboard
import com.dzakaryan.fasttyper.presentation.typing.end.TypingEndFragment.Companion.ARG_LAST_WPM
import kotlinx.android.synthetic.main.fragment_typing_process.*
import org.koin.android.viewmodel.ext.android.viewModel

class TypingProcessFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingProcessViewModel by viewModel()
    private lateinit var styleSpan: StyleSpan
    private var isMistakeShown: Boolean = false
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_typing_process

    override fun retrieveViewData(bundle: Bundle?) {
        viewModel.textToType = bundle?.getString(ARG_RANDOM_TEXT)
    }

    override fun initViews(view: View) {
        textToType.movementMethod = ScrollingMovementMethod()
        textToType.setText(viewModel.textToType, TextView.BufferType.SPANNABLE)
        viewModel.setSpannableText(textToType.text as Spannable)

        fakeEditText.showSoftKeyboard()
        fakeEditText.afterTextChanged {
            viewModel.afterTextChanged(it)
        }

        //TODO create some timer here
        viewModel.startTypingProcess()
    }

    override fun observeData() {
        viewModel.wpmValueLiveData.observe(viewLifecycleOwner, Observer {
            wpmValue.text = it
        })
        viewModel.timerValueLiveData.observe(viewLifecycleOwner, Observer {
            timerValue.text = it
        })
        viewModel.typingStatusLiveData.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                TypingProcessViewModel.TypingStatus.STARTED -> TODO()
                TypingProcessViewModel.TypingStatus.FINISHED -> showFinishedPage()
                else -> Unit
            }
        })
        viewModel.clearTypedText.observe(viewLifecycleOwner, Observer {
            if (it) {
                fakeEditText.text.clear()
            }
        })
    }

    override fun onDestroyView() {
        requireActivity().hideSoftKeyboard()
        super.onDestroyView()
    }
    //endregion

    //region Private utility methods
    private fun showFinishedPage() {
        val bundle = bundleOf(ARG_LAST_WPM to viewModel.lastWpm)
        findNavController().navigate(R.id.action_typingProcessFragment_to_typingEndFragment, bundle)
    }
    //endregion

    //region Companion object
    companion object {
        const val ARG_RANDOM_TEXT = "arg.RANDOM_TEXT"
    }
    //endregion
}