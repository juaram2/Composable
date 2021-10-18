package com.example.composable.model.account

import com.squareup.moshi.Json

data class ChangeEmailModel (
    @Json(name = "email")
    val email: kotlin.String
)
