package com.example.composable.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun VideoThumbnail(url: String) {
    // TODO : replace with youtube player
    val thumbnail = url.replace("www.youtube.com/embed", "i.ytimg.com/vi")
    Image(painter = rememberImagePainter("$thumbnail/maxresdefault.jpg"),
        contentDescription = "thumbnail",
        Modifier.height(180.dp).size(320.dp)
    )
}

