package com.example.composable.ui.detailPages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchResult(
    onClick: () -> Unit,
    keywords: List<String>
) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    Log.d("debug", "searchTerm2: $keywords")

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        keywords.forEach { keyword ->
            Text(keyword, modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}
