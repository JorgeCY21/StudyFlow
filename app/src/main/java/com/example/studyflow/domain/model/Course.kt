package com.example.studyflow.domain.model

data class Course(
    val id: Int,
    val name: String,
    val teacher: String,
    val taskCount: Int,
    val colorHex: String = "#2563EB",
)
