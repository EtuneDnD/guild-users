package com.guild.users.domain.drivingports

import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.commands.CreateUserAbstractCommand
import com.guild.users.domain.commands.GenerateLoginTokenCommand
import com.guild.users.domain.model.User

interface UserManagementDrivingPort {
    fun saveUser(createUserAbstractCommand: CreateUserAbstractCommand) : User
    fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand): String
    fun addRolesToUser(addRoleCommand: AddRoleCommand)
}