package com.dzakaryan.fasttyper.presentation.typing.start

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.dzakaryan.fasttyper.presentation.typing.process.TypingProcessFragment.Companion.ARG_RANDOM_TEXT
import kotlinx.android.synthetic.main.fragment_typing_start.*
import org.koin.android.viewmodel.ext.android.viewModel

class TypingStartFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingStartViewModel by viewModel()
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_typing_start

    override fun initViews(view: View) {
        viewModel.onInitViews()

        primaryActionButton.setOnClickListener {
            val bundle = bundleOf(ARG_RANDOM_TEXT to viewModel.randomText)
            it.findNavController()
                .navigate(R.id.action_typingStartFragment_to_typingProcessFragment, bundle)
        }
    }
    //endregion
}