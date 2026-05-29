package com.example.studyflow.presentation.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.studyflow.presentation.tasks.components.TaskList

@Composable
fun TaskRoute(
    innerPadding: PaddingValues,
    viewModel: TaskViewModel = viewModel(factory = StudyFlowContainer.taskViewModelFactory()),
) {
    val state by viewModel.uiState.collectAsState()
    TaskScreen(
        uiState = state,
        onTaskChecked = viewModel::onTaskChecked,
        onAddTask = viewModel::addTask,
        modifier = Modifier.padding(innerPadding),
    )
}

@Composable
fun TaskScreen(
    uiState: TaskUiState,
    onTaskChecked: (Int, Boolean) -> Unit,
    onAddTask: (String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        SectionTitle(
            title = "Tareas académicas",
            actionText = "Agregar",
            onActionClick = { showAddDialog = true },
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.tasks.isEmpty()) {
            EmptyState(
                title = "No hay tareas disponibles",
                message = "Cuando cargues nuevas tareas aparecerán aquí.",
            )
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                TaskList(
                    tasks = uiState.tasks,
                    onCheckedChange = onTaskChecked,
                )
            }
        }
    }

    if (showAddDialog) {
        AddTaskDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { title, courseName, deadline ->
                onAddTask(title, courseName, deadline)
                showAddDialog = false
            },
        )
    }
}

@Composable
private fun AddTaskDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var courseName by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Nueva tarea") },
        text = {
            Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                )
                OutlinedTextField(
                    value = courseName,
                    onValueChange = { courseName = it },
                    label = { Text("Curso asociado") },
                )
                OutlinedTextField(
                    value = deadline,
                    onValueChange = { deadline = it },
                    label = { Text("Fecha límite") },
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(title.trim(), courseName.trim(), deadline.trim()) },
                enabled = title.isNotBlank() && courseName.isNotBlank() && deadline.isNotBlank(),
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
