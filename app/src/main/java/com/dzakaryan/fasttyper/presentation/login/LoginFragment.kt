package com.dzakaryan.fasttyper.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dzakaryan.fasttyper.FastTyperApp
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    //region Properties
    private val viewModel: LoginViewModel by lazy { injectViewModel() }
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as FastTyperApp)
            .applicationComponent.inject(viewModel)


        auth = Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInViewFirebase.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, FIREBASE_SIGN_IN_RC)
        }
        initGoogleSignInClient()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FIREBASE_SIGN_IN_RC) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Login", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Login", "Google sign in failed", e)
                // ...
            }
        }
    }

    //endregion

    //region Private utility methods
    private fun injectViewModel() = ViewModelProvider(this).get(LoginViewModel::class.java)

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    // ...
                    //Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    //endregion


    ///region Companion object
    companion object {
        private const val FIREBASE_SIGN_IN_RC = 200
        private const val TAG = "Login"
    }
    //endregion
}