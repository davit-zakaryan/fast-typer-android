package com.dzakaryan.fasttyper.presentation.main

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dzakaryan.fasttyper.domain.repository.AuthenticationRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val authRepository: AuthenticationRepository,
) : BaseViewModel(application) {


    fun onLogoutClick() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }


}