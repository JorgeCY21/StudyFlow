package com.example.studyflow.domain.model

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val completed: Boolean,
)
