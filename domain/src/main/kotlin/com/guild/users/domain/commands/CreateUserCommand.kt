package com.guild.users.domain.commands

data class CreateUserCommand(
    val email: String,
    val password: String
)