package com.dzakaryan.fasttyper.presentation.typing.process

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

class TypingProcessViewModel(
    application: Application,

    ) : BaseViewModel(application) {



    init {

        val tickerChannel = ticker(delayMillis = 60_000, initialDelayMillis = 100)
        viewModelScope.launch {
            for (event in tickerChannel) {
                val currentTime = System.currentTimeMillis()
                println("aaaaaaaaaa" + currentTime)
            }
            // delay(tickerChannel.)
        }
    }
}