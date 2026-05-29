package com.example.studyflow.presentation.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.studyflow.navigation.Screen

@Composable
fun AppBottomNavigation(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
) {
    NavigationBar {
        Screen.bottomNavigationItems.forEach { screen ->
            val selected = currentRoute == screen.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(screen.route) },
                icon = {
                    androidx.compose.material3.Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                    )
                },
                label = {
                    Text(text = screen.label)
                },
                colors = NavigationBarItemDefaults.colors(),
            )
        }
    }
}
