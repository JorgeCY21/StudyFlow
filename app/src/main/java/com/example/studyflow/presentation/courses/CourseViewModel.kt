package com.example.studyflow.presentation.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyflow.domain.usecase.AddCourseUseCase
import com.example.studyflow.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CourseViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val addCourseUseCase: AddCourseUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    init {
        getCoursesUseCase()
            .onEach { courses ->
                _uiState.value = CourseUiState(courses = courses)
            }
            .launchIn(viewModelScope)
    }

    fun addCourse(name: String, teacher: String, taskCount: Int) {
        viewModelScope.launch {
            addCourseUseCase(name, teacher, taskCount)
        }
    }
}
