package com.example.composable.model.account

import com.squareup.moshi.Json

data class ConfirmEmailModel (
        @Json(name = "code")
        val code: kotlin.String
)
