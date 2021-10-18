package com.example.composable.model.account

import com.squareup.moshi.Json

data class ResetPasswordViewModel (
        @Json(name = "email")
        val email: kotlin.String,
        @Json(name = "password")
        val password: kotlin.String,
        @Json(name = "confirmPassword")
        val confirmPassword: kotlin.String? = null,
        @Json(name = "code")
        val code: kotlin.String? = null
)
