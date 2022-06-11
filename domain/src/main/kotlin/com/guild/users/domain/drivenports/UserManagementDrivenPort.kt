package com.guild.users.domain.drivenports

import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.commands.CreateBaseUserCommand

interface UserManagementDrivenPort {
    fun createUser(createUserCommand: CreateBaseUserCommand)
    fun addRoleToUser(addRoleCommand: AddRoleCommand)
}