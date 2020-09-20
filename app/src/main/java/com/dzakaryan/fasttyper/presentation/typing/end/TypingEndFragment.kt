package com.dzakaryan.fasttyper.presentation.typing.end

import android.os.Bundle
import android.view.View
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_typing_end.*
import org.koin.android.viewmodel.ext.android.viewModel

class TypingEndFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingEndViewModel by viewModel()
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_typing_end

    override fun retrieveViewData(bundle: Bundle?) {
        viewModel.lastWmp = bundle?.getString(ARG_LAST_WPM)
            ?: throw IllegalArgumentException("argument wpm not provided")
    }

    override fun initViews(view: View) {
        resultWmpValue.text = viewModel.lastWmp

        primaryActionButton.setOnClickListener {

        }
    }
    //endregion

    //region Companion object
    companion object {
        const val ARG_LAST_WPM = "arg.LAST_WPM"
    }
    //endregion
}