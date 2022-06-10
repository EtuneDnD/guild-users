package com.guild.users.domain.drivingports

import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.domain.commands.GenerateLoginTokenCommand
import com.guild.users.domain.model.Player

interface UserManagementDrivingPort {
    fun saveUser(createPlayerCommand: CreatePlayerCommand) : Player
    fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand): String
    fun addRolesToUser(addRoleCommand: AddRoleCommand)
}