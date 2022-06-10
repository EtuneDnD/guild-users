package com.guild.users.domain.drivenports

import com.guild.users.domain.commands.GenerateLoginTokenCommand

interface AuthorizeUserDrivenPort {
    fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand): String
}