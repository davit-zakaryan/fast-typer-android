package com.dzakaryan.fasttyper.di.module

import com.dzakaryan.fasttyper.presentation.core.MainViewModel
import com.dzakaryan.fasttyper.presentation.leaderboard.LeaderboardViewModel
import com.dzakaryan.fasttyper.presentation.login.LoginViewModel
import com.dzakaryan.fasttyper.presentation.settings.SettingsViewModel
import com.dzakaryan.fasttyper.presentation.statistics.StatisticsViewModel
import com.dzakaryan.fasttyper.presentation.typing.end.TypingEndViewModel
import com.dzakaryan.fasttyper.presentation.typing.process.TypingProcessViewModel
import com.dzakaryan.fasttyper.presentation.typing.start.TypingStartViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    viewModel { MainViewModel(androidApplication(), get()) }

    viewModel { LeaderboardViewModel(androidApplication()) }

    viewModel { LoginViewModel(androidApplication(), get(), get(), get()) }

    viewModel { SettingsViewModel(androidApplication()) }

    viewModel { StatisticsViewModel(androidApplication(), get(), get()) }

    viewModel { TypingEndViewModel(androidApplication(), get()) }

    viewModel { TypingProcessViewModel(androidApplication()) }

    viewModel { TypingStartViewModel(androidApplication(), get()) }
}