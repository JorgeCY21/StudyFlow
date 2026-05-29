package com.example.studyflow.presentation.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.domain.usecase.GetProgressSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProgressViewModel(
    private val getProgressSummaryUseCase: GetProgressSummaryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()

    init {
        getProgressSummaryUseCase()
            .onEach { summary ->
                _uiState.value = ProgressUiState(summary = summary)
            }
            .launchIn(viewModelScope)
    }
}
