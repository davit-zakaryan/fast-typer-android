package com.dzakaryan.fasttyper.presentation.typing.end

import android.app.Application
import com.dzakaryan.fasttyper.domain.repository.HistoryRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel

class TypingEndViewModel(
    application: Application,
    val historyRepository: HistoryRepository,
) : BaseViewModel(application) {


}