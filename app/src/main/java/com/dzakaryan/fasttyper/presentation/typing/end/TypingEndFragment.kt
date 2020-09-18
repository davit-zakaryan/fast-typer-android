package com.dzakaryan.fasttyper.presentation.typing.end

import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TypingEndFragment : BaseFragment() {

    //region Properties
    private val viewModel: TypingEndViewModel by viewModel()
    //endregion

    override fun getLayoutId() = R.layout.fragment_typing_end
}