package com.dzakaryan.fasttyper.presentation.login

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel(
    application: Application,
    val googleSignInClient: GoogleSignInClient
) : BaseViewModel(application) {

    //region Properties
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val _userLiveData = MutableLiveData<FirebaseUser?>()
    val userLiveData: LiveData<FirebaseUser?> get() = _userLiveData
    //endregion

    //region Public methods
    fun googleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val googleSignInAccount = task.getResult(ApiException::class.java)
            googleSignInAccount?.let {
                Log.d(TAG, "firebaseAuthWithGoogle:${it.id}")
                firebaseAuthWithGoogle(it.idToken)
            }
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Log.d(TAG, "Google sign in failed", e)
            //TODO show some failed dialog via LiveData
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                _userLiveData.value = auth.currentUser
            } else {
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                _userLiveData.value = null
            }
        }
    }
    //endregion

    //region Companion object
    companion object {
        const val TAG = "Login"
    }
    //endregion
}