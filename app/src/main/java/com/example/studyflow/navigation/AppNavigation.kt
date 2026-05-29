package com.example.studyflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyflow.presentation.components.AppScaffold
import com.example.studyflow.presentation.courses.CourseRoute
import com.example.studyflow.presentation.habits.HabitRoute
import com.example.studyflow.presentation.home.HomeRoute
import com.example.studyflow.presentation.progress.ProgressRoute
import com.example.studyflow.presentation.tasks.TaskRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val currentScreen = Screen.fromRoute(currentDestination)

    AppScaffold(
        currentScreen = currentScreen,
        onNavigate = { route ->
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                HomeRoute(innerPadding = innerPadding)
            }
            composable(Screen.Habits.route) {
                HabitRoute(innerPadding = innerPadding)
            }
            composable(Screen.Tasks.route) {
                TaskRoute(innerPadding = innerPadding)
            }
            composable(Screen.Courses.route) {
                CourseRoute(innerPadding = innerPadding)
            }
            composable(Screen.Progress.route) {
                ProgressRoute(innerPadding = innerPadding)
            }
        }
    }
}
