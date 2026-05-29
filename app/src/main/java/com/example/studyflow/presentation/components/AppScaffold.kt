package com.example.studyflow.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.studyflow.navigation.Screen

@Composable
fun AppScaffold(
    currentScreen: Screen?,
    onNavigate: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            AppTopBar(title = currentScreen?.label ?: "StudyFlow")
        },
        bottomBar = {
            AppBottomNavigation(
                currentRoute = currentScreen?.route,
                onNavigate = onNavigate,
            )
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}
