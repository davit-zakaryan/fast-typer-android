package com.dzakaryan.fasttyper.presentation.typing.start

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.dzakaryan.fasttyper.presentation.typing.process.TypingProcessViewModel
import kotlinx.android.synthetic.main.fragment_typing_start.*
import org.koin.android.viewmodel.ext.android.viewModel

class TypingStartFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingProcessViewModel by viewModel()
    //endregion

    override fun getLayoutId() = R.layout.fragment_typing_start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        primaryActionButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_typingStartFragment_to_typingProcessFragment)
        }
    }
}