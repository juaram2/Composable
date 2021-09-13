package com.example.composable.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import com.example.composable.TopBar
import com.example.composable.ui.components.CustomSearchBar

@Composable
fun Search() {
    Column() {
        TopBar()
        CustomSearchBar()
    }
}