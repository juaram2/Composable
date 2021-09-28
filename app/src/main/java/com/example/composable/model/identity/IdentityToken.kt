package com.example.composable.model.identity

data class IdentityToken(
    val access_token: String = "",
    val expires_in: Int = 0,
    val token_type: String = "",
    val refresh_token: String = "",
    val scope: String = ""
)