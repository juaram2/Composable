package com.example.composable.model.exception

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GrantValidationResult (
    var error: String,
    var error_description: String,
    var userInfo: UserInfo?,
    var errorCode: String?
)
