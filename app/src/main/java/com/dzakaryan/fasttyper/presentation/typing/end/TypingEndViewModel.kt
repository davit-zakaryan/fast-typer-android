package com.dzakaryan.fasttyper.presentation.typing.end

import android.app.Application
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel

class TypingEndViewModel(
    application: Application,
    val textRepository: TextRepository,
) : BaseViewModel(application) {


}