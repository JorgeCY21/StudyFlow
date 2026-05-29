package com.example.studyflow.presentation.habits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studyflow.di.StudyFlowContainer
import com.example.studyflow.presentation.components.EmptyState
import com.example.studyflow.presentation.components.SectionTitle
import com.example.studyflow.presentation.habits.components.HabitList

@Composable
fun HabitRoute(
    innerPadding: PaddingValues,
    viewModel: HabitViewModel = viewModel(factory = StudyFlowContainer.habitViewModelFactory()),
) {
    val state by viewModel.uiState.collectAsState()
    HabitScreen(
        uiState = state,
        onHabitChecked = viewModel::onHabitChecked,
        onAddHabit = viewModel::addHabit,
        modifier = Modifier.padding(innerPadding),
    )
}

@Composable
fun HabitScreen(
    uiState: HabitUiState,
    onHabitChecked: (Int, Boolean) -> Unit,
    onAddHabit: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        SectionTitle(
            title = "Hábitos académicos",
            actionText = "Agregar",
            onActionClick = { showAddDialog = true },
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.habits.isEmpty()) {
            EmptyState(
                title = "No hay hábitos disponibles",
                message = "Agrega hábitos académicos para empezar a organizar tu semana.",
            )
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                HabitList(
                    habits = uiState.habits,
                    onCheckedChange = onHabitChecked,
                )
            }
        }
    }

    if (showAddDialog) {
        AddHabitDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, description, category ->
                onAddHabit(name, description, category)
                showAddDialog = false
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddHabitDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Nuevo hábito") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoría") },
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name.trim(), description.trim(), category.trim()) },
                enabled = name.isNotBlank() && description.isNotBlank() && category.isNotBlank(),
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
    )
}
