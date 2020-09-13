package com.dzakaryan.fasttyper.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    val isUsedSignIn: Boolean = true
}