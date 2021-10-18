package com.example.composable.model.account

import com.squareup.moshi.Json

data class SetPasswordRequestModel (
        @Json(name = "newPassword")
        val newPassword: kotlin.String,
        @Json(name = "oldPassword")
        val oldPassword: kotlin.String? = null,
        @Json(name = "confirmPassword")
        val confirmPassword: kotlin.String? = null
)
