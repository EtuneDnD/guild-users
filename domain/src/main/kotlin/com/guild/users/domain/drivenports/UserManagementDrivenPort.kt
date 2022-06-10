package com.guild.users.domain.drivenports

import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.commands.CreateUserCommand

interface UserManagementDrivenPort {
    fun createUser(createUserCommand: CreateUserCommand)
    fun addRoleToUser(addRoleCommand: AddRoleCommand)
}