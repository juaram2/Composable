package com.example.composable.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter

@Composable
fun Thumbnail(painter: ImagePainter) {
    when (painter.state) {
        is ImagePainter.State.Loading -> {
            CircularProgressIndicator()
        }
        is ImagePainter.State.Error -> {
            Log.d("debug", "Error!!!")
        }
        else -> {
            Log.d("debug", "state: ${painter.state}")
        }
    }

    Image(
        painter = painter,
        contentDescription = "Hospital Image",
        modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(RoundedCornerShape(5.dp)),
        alignment = Alignment.Center,
        contentScale = ContentScale.FillBounds,
    )
}