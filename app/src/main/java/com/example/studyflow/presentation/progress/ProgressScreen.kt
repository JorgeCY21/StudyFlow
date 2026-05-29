package com.example.studyflow.presentation.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.studyflow.presentation.components.SectionTitle
import com.example.studyflow.presentation.progress.components.ProgressStatCard

@Composable
fun ProgressRoute(
    innerPadding: PaddingValues,
    viewModel: ProgressViewModel = viewModel(factory = StudyFlowContainer.progressViewModelFactory()),
) {
    val state by viewModel.uiState.collectAsState()
    ProgressScreen(
        uiState = state,
        modifier = Modifier.padding(innerPadding),
    )
}

@Composable
fun ProgressScreen(
    uiState: ProgressUiState,
    modifier: Modifier = Modifier,
) {
    val summary = uiState.summary
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SectionTitle(title = "Progreso general")
        ProgressStatCard(
            title = "Tareas totales",
            value = summary.totalTasks.toString(),
            subtitle = "Cantidad total de tareas registradas",
        )
        ProgressStatCard(
            title = "Tareas completadas",
            value = summary.completedTasks.toString(),
            subtitle = "Tareas marcadas como hechas",
        )
        ProgressStatCard(
            title = "Hábitos completados",
            value = summary.completedHabits.toString(),
            subtitle = "Hábitos académicos logrados",
        )
        ProgressStatCard(
            title = "Avance general",
            value = "${summary.weeklyProgressPercent}%",
            subtitle = "Promedio de avance semanal",
        )
        Column {
            Text(
                text = "Progreso visual",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { summary.weeklyProgressPercent / 100f },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
