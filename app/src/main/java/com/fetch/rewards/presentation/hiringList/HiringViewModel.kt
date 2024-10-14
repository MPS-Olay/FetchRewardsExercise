package com.fetch.rewards.presentation.hiringList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.rewards.domain.usecase.GetHiringListFormattedUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HiringViewModel(
    private val hiringListFormattedUseCase: GetHiringListFormattedUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _state.update { it.copy(isLoading = false, error = "Something went wrong!") }
    }
    private val _state = MutableStateFlow(HiringListState(isLoading = true))
    val state: StateFlow<HiringListState> get() = _state

    init {
        viewModelScope.launch(exceptionHandler) {
            val hiringMap = hiringListFormattedUseCase()
            _state.update { it.copy(isLoading = false, hiringMap = hiringMap) }
        }
    }
}
