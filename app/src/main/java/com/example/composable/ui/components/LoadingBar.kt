package com.example.composable.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composable.ui.theme.Blue200

@Composable
fun LoadingBar() {
    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            CircularProgressIndicator(
                color = Blue200,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(45.dp)
            )
        }
    }
}