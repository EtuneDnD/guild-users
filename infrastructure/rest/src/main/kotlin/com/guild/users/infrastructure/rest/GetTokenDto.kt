package com.guild.users.infrastructure.rest

data class GetTokenDto(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)