package com.dzakaryan.fasttyper.presentation.statistics

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dzakaryan.fasttyper.domain.repository.HistoryRepository
import com.dzakaryan.fasttyper.domain.repository.UserRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class StatisticsViewModel(
    application: Application,
    private val historyRepository: HistoryRepository,
    private val userRepository: UserRepository,
) : BaseViewModel(application) {


    fun getPlayingHistory() {
        viewModelScope.launch {
            val stats = historyRepository.requestAllHistory(userRepository.getUser())
        }
    }
}