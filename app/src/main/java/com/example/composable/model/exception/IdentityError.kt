package com.example.composable.model.exception

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IdentityError (
    var code: String,
    var description: String
)
