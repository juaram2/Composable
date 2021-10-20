package com.example.composable.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.composable.model.identity.UserInfo

@Composable
fun Setting() {
    Column() {
        AppScreen(UserInfo())
    }
}