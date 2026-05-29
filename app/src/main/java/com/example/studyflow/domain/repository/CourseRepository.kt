package com.example.studyflow.domain.repository

import com.example.studyflow.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun observeCourses(): Flow<List<Course>>

    suspend fun addCourse(name: String, teacher: String, taskCount: Int)
}
