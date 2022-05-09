package com.guild.users

data class GetTokenDto(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)