package com.dzakaryan.fasttyper.presentation.typing.start

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dzakaryan.fasttyper.domain.repository.TextRepository
import com.dzakaryan.fasttyper.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class TypingStartViewModel(
    application: Application,
    private val textRepository: TextRepository,
) : BaseViewModel(application) {

    //region Properties
    lateinit var randomText: String
    private val _primaryButtonEnabledLiveData = MutableLiveData<Boolean>()
    val primaryButtonEnabledLiveData: LiveData<Boolean> get() = _primaryButtonEnabledLiveData
    //endregion

    fun onInitViews() {
        viewModelScope.launch {
            randomText = textRepository.getRandomText()
            _primaryButtonEnabledLiveData.value = true
        }
    }
}