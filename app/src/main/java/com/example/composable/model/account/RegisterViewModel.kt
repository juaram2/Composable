package com.example.composable.model.account

import com.squareup.moshi.Json

data class RegisterViewModel (
        @Json(name = "email")
        val email: kotlin.String,
        @Json(name = "password")
        val password: kotlin.String,
        @Json(name = "userName")
        val userName: kotlin.String? = null,
        @Json(name = "confirmPassword")
        val confirmPassword: kotlin.String? = null
)
