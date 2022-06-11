package com.guild.users.domain.drivingports

import com.guild.users.domain.commands.*
import com.guild.users.domain.model.Player

interface UserManagementDrivingPort {
    fun saveUser(createUserAbstractCommand: CreateUserAbstractCommand) : Player
    fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand): String
    fun addRolesToUser(addRoleCommand: AddRoleCommand)
}