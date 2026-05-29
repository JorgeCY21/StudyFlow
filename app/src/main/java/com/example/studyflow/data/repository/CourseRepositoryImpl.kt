package com.example.studyflow.data.repository

import com.example.studyflow.data.datasource.LocalCourseDataSource
import com.example.studyflow.domain.model.Course
import com.example.studyflow.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class CourseRepositoryImpl(
    private val localCourseDataSource: LocalCourseDataSource,
) : CourseRepository {
    override fun observeCourses(): Flow<List<Course>> = localCourseDataSource.courses

    override suspend fun addCourse(name: String, teacher: String, taskCount: Int) {
        localCourseDataSource.addCourse(name, teacher, taskCount)
    }
}
