package com.example.studyflow.presentation.progress.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.studyflow.presentation.components.AppStatCard

@Composable
fun ProgressStatCard(
    title: String,
    value: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    AppStatCard(
        title = title,
        value = value,
        subtitle = subtitle,
        modifier = modifier,
    )
}
