package com.example.studyflow.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.domain.usecase.GetProgressSummaryUseCase
import com.example.studyflow.domain.usecase.GetStudentProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getStudentProfileUseCase: GetStudentProfileUseCase,
    private val getProgressSummaryUseCase: GetProgressSummaryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        combine(
            getStudentProfileUseCase(),
            getProgressSummaryUseCase(),
        ) { profile, summary ->
            HomeUiState(
                studentProfile = profile,
                progressSummary = summary,
            )
        }
            .onEach { state ->
                _uiState.value = state
            }
            .launchIn(viewModelScope)
    }
}
