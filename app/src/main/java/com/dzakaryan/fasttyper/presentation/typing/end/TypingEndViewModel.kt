package com.dzakaryan.fasttyper.presentation.typing.end

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dzakaryan.fasttyper.domain.repository.HistoryRepository
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class TypingEndViewModel(
    application: Application,
    val textRepository: TextRepository,
    val historyRepository: HistoryRepository,
) : BaseViewModel(application) {

    //region Properties
    lateinit var lastWmp: String
    //endregion

    init {
        viewModelScope.launch {
            //historyRepository.saveLastResult()
        }
    }
}