package com.guild.users.domain.commands

data class GenerateLoginTokenCommand(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)