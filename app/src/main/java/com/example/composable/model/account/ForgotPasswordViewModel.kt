package com.example.composable.model.account

import com.squareup.moshi.Json

data class ForgotPasswordViewModel (
        @Json(name = "email")
        val email: kotlin.String
)
