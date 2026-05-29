package com.example.studyflow.presentation.habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.domain.usecase.AddHabitUseCase
import com.example.studyflow.domain.usecase.GetHabitsUseCase
import com.example.studyflow.domain.usecase.SetHabitCompletedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HabitViewModel(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val addHabitUseCase: AddHabitUseCase,
    private val setHabitCompletedUseCase: SetHabitCompletedUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HabitUiState())
    val uiState: StateFlow<HabitUiState> = _uiState.asStateFlow()

    init {
        getHabitsUseCase()
            .onEach { habits ->
                _uiState.value = HabitUiState(habits = habits)
            }
            .launchIn(viewModelScope)
    }

    fun addHabit(name: String, description: String, category: String) {
        viewModelScope.launch {
            addHabitUseCase(name, description, category)
        }
    }

    fun onHabitChecked(habitId: Int, completed: Boolean) {
        viewModelScope.launch {
            setHabitCompletedUseCase(habitId, completed)
        }
    }
}
