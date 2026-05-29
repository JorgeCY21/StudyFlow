package com.example.studyflow.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studyflow.di.StudyFlowContainer
import com.example.studyflow.presentation.components.AppStatCard
import com.example.studyflow.presentation.components.SectionTitle

@Composable
fun HomeRoute(
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = viewModel(factory = StudyFlowContainer.homeViewModelFactory()),
) {
    val state by viewModel.uiState.collectAsState()
    HomeScreen(
        uiState = state,
        modifier = Modifier.padding(innerPadding),
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            ProfileCard(uiState = uiState)
        }
        item {
            SectionTitle(title = "Resumen semanal")
        }
        item {
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val cardWidth = (maxWidth - 12.dp) / 2
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    AppStatCard(
                        title = "Tareas pendientes",
                        value = uiState.progressSummary.pendingTasks.toString(),
                        subtitle = "Tareas aún por completar",
                        modifier = Modifier.width(cardWidth),
                        accentColor = MaterialTheme.colorScheme.primary,
                    )
                    AppStatCard(
                        title = "Hábitos completados",
                        value = uiState.progressSummary.completedHabits.toString(),
                        subtitle = "Logrados esta semana",
                        modifier = Modifier.width(cardWidth),
                        accentColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
        item {
            AppStatCard(
                title = "Hábitos por completar",
                value = uiState.progressSummary.pendingHabits.toString(),
                subtitle = "Hábitos todavía pendientes",
                accentColor = MaterialTheme.colorScheme.secondary,
            )
        }
        item {
            AppStatCard(
                title = "Progreso semanal",
                value = "${uiState.progressSummary.weeklyProgressPercent}%",
                subtitle = "${uiState.progressSummary.completedTasks} de ${uiState.progressSummary.totalTasks} tareas completadas",
                accentColor = MaterialTheme.colorScheme.tertiary,
            ) {
                LinearProgressIndicator(
                    progress = { uiState.progressSummary.weeklyProgressPercent / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                )
            }
        }
    }
}

@Composable
private fun ProfileCard(
    uiState: HomeUiState,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = uiState.studentProfile.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = uiState.studentProfile.program,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = uiState.studentProfile.semester,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                text = uiState.studentProfile.focusGoal,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}
