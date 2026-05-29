package com.example.studyflow.domain.model

data class Task(
    val id: Int,
    val title: String,
    val courseName: String,
    val deadline: String,
    val completed: Boolean,
)
