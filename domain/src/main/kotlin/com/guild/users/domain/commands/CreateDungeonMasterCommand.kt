package com.guild.users.domain.commands

import com.guild.users.domain.model.Role

data class CreateDungeonMasterCommand(
    override val userName: String,
    override val email: String,
    override val password: String,
    override val role: Role = Role.PLAYER,
    override val profileDescription: String,
    val rank: Int,
): CreateUserAbstractCommand()
