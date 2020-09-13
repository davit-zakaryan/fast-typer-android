package com.dzakaryan.fasttyper.di.component

import com.dzakaryan.fasttyper.di.module.AppModule
import com.dzakaryan.fasttyper.di.module.NetworkModule
import com.dzakaryan.fasttyper.presentation.login.LoginFragment
import com.dzakaryan.fasttyper.presentation.login.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {

    fun inject(viewModel: LoginViewModel)

    fun inject(fragment: LoginFragment)
}