package com.dzakaryan.fasttyper.presentation.login

import android.app.Application
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel(
    application: Application,
    val googleSignInClient: GoogleSignInClient
) : BaseViewModel(application) {

    lateinit var auth: FirebaseAuth


    val isUsedSignIn: Boolean = true

    fun getCurrentUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    fun onCreate() {
        auth = Firebase.auth
    }

    //fun isLoggedIn
}