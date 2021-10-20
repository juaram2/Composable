package com.example.composable.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VideoThumbnail() {
    Text(text = "Youtube", Modifier.fillMaxWidth().height(150.dp))
}