package com.example.composable.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.composable.TopBar

@Composable
fun Setting() {
    Column() {
        TopBar()
        Text(text = "Setting")
    }
}