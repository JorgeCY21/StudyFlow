package com.example.studyflow.presentation.courses

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
import com.example.studyflow.presentation.courses.components.CourseList

@Composable
fun CourseRoute(
    innerPadding: PaddingValues,
    viewModel: CourseViewModel = viewModel(factory = StudyFlowContainer.courseViewModelFactory()),
) {
    val state by viewModel.uiState.collectAsState()
    CourseScreen(
        uiState = state,
        onAddCourse = viewModel::addCourse,
        modifier = Modifier.padding(innerPadding),
    )
}

@Composable
fun CourseScreen(
    uiState: CourseUiState,
    onAddCourse: (String, String, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        SectionTitle(
            title = "Cursos del estudiante",
            actionText = "Agregar",
            onActionClick = { showAddDialog = true },
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.courses.isEmpty()) {
            EmptyState(
                title = "No hay cursos registrados",
                message = "Aquí verás los cursos vinculados al semestre actual.",
            )
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                CourseList(courses = uiState.courses)
            }
        }
    }

    if (showAddDialog) {
        AddCourseDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { name, teacher, taskCount ->
                onAddCourse(name, teacher, taskCount)
                showAddDialog = false
            },
        )
    }
}

@Composable
private fun AddCourseDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Int) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var teacher by remember { mutableStateOf("") }
    var taskCountText by remember { mutableStateOf("1") }

    val taskCount = taskCountText.toIntOrNull()?.takeIf { it > 0 }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Nuevo curso") },
        text = {
            Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre del curso") },
                )
                OutlinedTextField(
                    value = teacher,
                    onValueChange = { teacher = it },
                    label = { Text("Docente") },
                )
                OutlinedTextField(
                    value = taskCountText,
                    onValueChange = { taskCountText = it.filter { char -> char.isDigit() } },
                    label = { Text("Cantidad de tareas") },
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name.trim(), teacher.trim(), taskCount ?: 1) },
                enabled = name.isNotBlank() && teacher.isNotBlank() && taskCount != null,
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
