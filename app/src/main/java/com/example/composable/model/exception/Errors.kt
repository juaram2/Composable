package com.example.composable.model.exception

import com.squareup.moshi.JsonClass

data class Errors(
    var message: List<String>
)
