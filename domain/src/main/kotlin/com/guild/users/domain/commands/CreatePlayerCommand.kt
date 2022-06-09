package com.guild.users.domain.commands

data class CreatePlayerCommand(
    val userName: String,
    val email: String,
    val profileDescription: String,
)
