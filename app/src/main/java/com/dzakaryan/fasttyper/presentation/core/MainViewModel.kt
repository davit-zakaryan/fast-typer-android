package com.dzakaryan.fasttyper.presentation.core

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dzakaryan.fasttyper.domain.repository.AuthenticationRepository
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