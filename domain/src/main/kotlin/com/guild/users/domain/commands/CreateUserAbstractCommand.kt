package com.guild.users.domain.commands

import com.guild.users.domain.model.Role

abstract class CreateUserAbstractCommand {
    abstract var userId: String
    abstract val userName: String
    abstract val email: String
    abstract val password: String
    abstract val role: Role
    abstract val profileDescription: String?
}
