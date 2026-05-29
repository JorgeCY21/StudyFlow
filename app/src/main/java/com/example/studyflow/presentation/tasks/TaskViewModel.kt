package com.example.studyflow.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.domain.usecase.AddTaskUseCase
import com.example.studyflow.domain.usecase.GetCoursesUseCase
import com.example.studyflow.domain.usecase.GetTasksUseCase
import com.example.studyflow.domain.usecase.SetTaskCompletedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val setTaskCompletedUseCase: SetTaskCompletedUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        combine(
            getTasksUseCase(),
            getCoursesUseCase(),
        ) { tasks, courses ->
            TaskUiState(
                tasks = tasks,
                courses = courses,
            )
        }
            .onEach { state ->
                _uiState.value = state
            }
            .launchIn(viewModelScope)
    }

    fun addTask(title: String, courseName: String, deadline: String) {
        viewModelScope.launch {
            addTaskUseCase(title, courseName, deadline)
        }
    }

    fun onTaskChecked(taskId: Int, completed: Boolean) {
        viewModelScope.launch {
            setTaskCompletedUseCase(taskId, completed)
        }
    }
}
