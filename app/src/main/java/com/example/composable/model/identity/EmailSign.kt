package com.example.composable.model.identity

data class EmailSign (
    var grant_type: GrantType = GrantType.password,
    var email: String,
    var password: String
)