package com.guild.users.application.commands

data class CreatePlayerCommand(
    val userName: String,
    val email: String,
    val profileDescription: String,
)
