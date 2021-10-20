package com.example.composable.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoResult(query: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            text = "No Result for $query", Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}