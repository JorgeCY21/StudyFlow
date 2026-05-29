package com.example.studyflow.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    data object Home : Screen("home", "Home", Icons.Outlined.Home)
    data object Habits : Screen("habits", "Hábitos", Icons.Outlined.CheckCircleOutline)
    data object Tasks : Screen("tasks", "Tareas", Icons.Outlined.Description)
    data object Courses : Screen("courses", "Cursos", Icons.Outlined.School)
    data object Progress : Screen("progress", "Progreso", Icons.Outlined.BarChart)

    companion object {
        val bottomNavigationItems = listOf(Home, Habits, Tasks, Courses, Progress)

        fun fromRoute(route: String?): Screen? = bottomNavigationItems.firstOrNull { it.route == route }
    }
}
