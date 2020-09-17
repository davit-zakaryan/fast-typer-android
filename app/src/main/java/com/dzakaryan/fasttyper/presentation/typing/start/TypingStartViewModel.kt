package com.dzakaryan.fasttyper.presentation.typing.start

import android.app.Application
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel

class TypingStartViewModel(
    application: Application,
    val textRepository: TextRepository
) : BaseViewModel(application) {


}