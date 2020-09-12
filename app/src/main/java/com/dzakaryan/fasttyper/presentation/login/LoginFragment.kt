package com.dzakaryan.fasttyper.presentation.login

import androidx.lifecycle.ViewModelProvider
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment

class LoginFragment : BaseFragment() {

    //region Properties
    private val viewModel: LoginViewModel by lazy { injectViewModel() }
    //endregion

    override fun getLayoutId() = R.layout.fragment_login


    private fun injectViewModel() = ViewModelProvider(this).get(LoginViewModel::class.java)
}