package com.example.studyflow.presentation.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenu
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
            courses = uiState.courses,
            onDismiss = { showAddDialog = false },
            onConfirm = { title, courseName, deadline ->
                onAddTask(title, courseName, deadline)
                showAddDialog = false
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskDialog(
    courses: List<com.example.studyflow.domain.model.Course>,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedCourse by remember(courses) { mutableStateOf(courses.firstOrNull()?.name.orEmpty()) }

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

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                ) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(),
                        value = selectedCourse,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Curso asociado") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        courses.forEach { course ->
                            DropdownMenuItem(
                                text = { Text(course.name) },
                                onClick = {
                                    selectedCourse = course.name
                                    expanded = false
                                },
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = deadline,
                    onValueChange = { deadline = it },
                    label = { Text("Fecha límite") },
                )

                if (courses.isEmpty()) {
                    Text(
                        text = "Primero crea un curso para poder asociar la tarea.",
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(title.trim(), selectedCourse, deadline.trim()) },
                enabled = title.isNotBlank() && selectedCourse.isNotBlank() && deadline.isNotBlank() && courses.isNotEmpty(),
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
