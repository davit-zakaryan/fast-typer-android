package com.dzakaryan.fasttyper.presentation.login

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.BaseFragment
import com.dzakaryan.fasttyper.presentation.core.DrawerLocker
import com.dzakaryan.fasttyper.presentation.core.NavigationHeaderView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    //region Properties
    private val viewModel: LoginViewModel by viewModel()
    //endregion

    //region Override open methods
    override fun getLayoutId() = R.layout.fragment_login

    override fun initViews(view: View) {
        (requireActivity() as? DrawerLocker)?.setDrawerLocked(true)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.hide()

        signInGoogleButton.setOnClickListener {
            val signInIntent = viewModel.googleSignInClient.signInIntent
            startActivityForResult(signInIntent, FIREBASE_SIGN_IN_RC)
        }
        signInGuestButton.setOnClickListener {
            findNavController().navigate(R.id.typingStartFragment, null)
        }
    }

    override fun observeData() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                val activity = requireActivity() as? NavigationHeaderView
                activity?.apply {
                    getDisplayNameView().text = it.displayName
                    getEmailView().text = it.email
                    Picasso.get().load(it.photoUrl).into(getHeaderImageView())
                    findNavController().navigate(R.id.typingStartFragment, null)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FIREBASE_SIGN_IN_RC) {
            viewModel.googleSignInResult(data)
        }
    }
    //endregion

    ///region Companion object
    companion object {
        private const val FIREBASE_SIGN_IN_RC = 200
    }
    //endregion
}