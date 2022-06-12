package com.guild.users.domain.commands

import com.guild.users.domain.model.Role

data class CreatePlayerCommand(
    override val userName: String,
    override val email: String,
    override val password: String,
    override val role: Role = Role.PLAYER,
    override val profileDescription: String?,
    val strikes: Int,
): CreateUserAbstractCommand()
