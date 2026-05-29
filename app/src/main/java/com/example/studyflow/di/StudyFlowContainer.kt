package com.example.studyflow.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.studyflow.data.datasource.LocalCourseDataSource
import com.example.studyflow.data.datasource.LocalHabitDataSource
import com.example.studyflow.data.datasource.LocalStudentDataSource
import com.example.studyflow.data.datasource.LocalTaskDataSource
import com.example.studyflow.data.repository.CourseRepositoryImpl
import com.example.studyflow.data.repository.HabitRepositoryImpl
import com.example.studyflow.data.repository.StudentRepositoryImpl
import com.example.studyflow.data.repository.TaskRepositoryImpl
import com.example.studyflow.domain.usecase.AddCourseUseCase
import com.example.studyflow.domain.usecase.AddHabitUseCase
import com.example.studyflow.domain.usecase.AddTaskUseCase
import com.example.studyflow.domain.usecase.GetCoursesUseCase
import com.example.studyflow.domain.usecase.GetHabitsUseCase
import com.example.studyflow.domain.usecase.GetProgressSummaryUseCase
import com.example.studyflow.domain.usecase.GetStudentProfileUseCase
import com.example.studyflow.domain.usecase.GetTasksUseCase
import com.example.studyflow.domain.usecase.SetHabitCompletedUseCase
import com.example.studyflow.domain.usecase.SetTaskCompletedUseCase
import com.example.studyflow.presentation.courses.CourseViewModel
import com.example.studyflow.presentation.habits.HabitViewModel
import com.example.studyflow.presentation.home.HomeViewModel
import com.example.studyflow.presentation.progress.ProgressViewModel
import com.example.studyflow.presentation.tasks.TaskViewModel

object StudyFlowContainer {

    private val habitDataSource = LocalHabitDataSource()
    private val taskDataSource = LocalTaskDataSource()
    private val courseDataSource = LocalCourseDataSource()
    private val studentDataSource = LocalStudentDataSource()

    private val habitRepository = HabitRepositoryImpl(habitDataSource)
    private val taskRepository = TaskRepositoryImpl(taskDataSource)
    private val courseRepository = CourseRepositoryImpl(courseDataSource)
    private val studentRepository = StudentRepositoryImpl(studentDataSource)

    private val getHabitsUseCase = GetHabitsUseCase(habitRepository)
    private val addHabitUseCase = AddHabitUseCase(habitRepository)
    private val setHabitCompletedUseCase = SetHabitCompletedUseCase(habitRepository)
    private val getTasksUseCase = GetTasksUseCase(taskRepository)
    private val addTaskUseCase = AddTaskUseCase(taskRepository)
    private val setTaskCompletedUseCase = SetTaskCompletedUseCase(taskRepository)
    private val getCoursesUseCase = GetCoursesUseCase(courseRepository)
    private val addCourseUseCase = AddCourseUseCase(courseRepository)
    private val getStudentProfileUseCase = GetStudentProfileUseCase(studentRepository)
    private val getProgressSummaryUseCase = GetProgressSummaryUseCase(
        habitRepository = habitRepository,
        taskRepository = taskRepository,
    )

    fun homeViewModelFactory(): ViewModelProvider.Factory = factoryOf {
        HomeViewModel(
            getStudentProfileUseCase = getStudentProfileUseCase,
            getProgressSummaryUseCase = getProgressSummaryUseCase,
        )
    }

    fun habitViewModelFactory(): ViewModelProvider.Factory = factoryOf {
        HabitViewModel(
            getHabitsUseCase = getHabitsUseCase,
            addHabitUseCase = addHabitUseCase,
            setHabitCompletedUseCase = setHabitCompletedUseCase,
        )
    }

    fun taskViewModelFactory(): ViewModelProvider.Factory = factoryOf {
        TaskViewModel(
            getTasksUseCase = getTasksUseCase,
            getCoursesUseCase = getCoursesUseCase,
            addTaskUseCase = addTaskUseCase,
            setTaskCompletedUseCase = setTaskCompletedUseCase,
        )
    }

    fun courseViewModelFactory(): ViewModelProvider.Factory = factoryOf {
        CourseViewModel(
            getCoursesUseCase = getCoursesUseCase,
            addCourseUseCase = addCourseUseCase,
        )
    }

    fun progressViewModelFactory(): ViewModelProvider.Factory = factoryOf {
        ProgressViewModel(
            getProgressSummaryUseCase = getProgressSummaryUseCase,
        )
    }

    private inline fun <reified T : ViewModel> factoryOf(
        crossinline create: () -> T,
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <VM : ViewModel> create(
                modelClass: Class<VM>,
                extras: CreationExtras,
            ): VM {
                return create() as VM
            }
        }
    }
}
