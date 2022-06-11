package com.guild.users.domain.commands

data class CreateBaseUserCommand(
    val email: String,
    val password: String
)