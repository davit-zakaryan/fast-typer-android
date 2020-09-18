package com.dzakaryan.fasttyper.di.module

import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.login.LoginViewModel
import com.dzakaryan.fasttyper.presentation.settings.SettingsViewModel
import com.dzakaryan.fasttyper.presentation.statistics.StatisticsViewModel
import com.dzakaryan.fasttyper.presentation.typing.process.TypingProcessViewModel
import com.dzakaryan.fasttyper.presentation.typing.start.TypingStartViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    // single instance of HelloRepository
    //single<HelloRepository> { HelloRepositoryImpl() }

    viewModel { LoginViewModel(androidApplication(), get(), get(), get()) }

    viewModel { SettingsViewModel(androidApplication()) }

    viewModel { StatisticsViewModel(androidApplication()) }

    viewModel { TypingProcessViewModel(androidApplication()) }

    viewModel { TypingStartViewModel(androidApplication(), get()) }

    single<GoogleSignInOptions> {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(androidContext().getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    single<GoogleSignInClient> { GoogleSignIn.getClient(androidContext(), get()) }


}