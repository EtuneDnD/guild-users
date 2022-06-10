package com.guild.users.infrastructure.firebase.adapters

import com.guild.users.domain.commands.GenerateLoginTokenCommand
import com.guild.users.domain.drivenports.AuthorizeUserDrivenPort
import com.guild.users.infrastructure.firebase.mappers.GenerateLoginMapper
import com.guild.users.infrastructure.firebase.rest.FireBaseAuthConsumer
import org.springframework.stereotype.Service

@Service
internal class GenerateLoginFirebaseDrivenAdapter(
    private val fireBaseAuthConsumer: FireBaseAuthConsumer,
    private val generateLoginMapper: GenerateLoginMapper
) : AuthorizeUserDrivenPort {

    override fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand): String =
        fireBaseAuthConsumer.getToken(generateLoginTokenCommand.let(generateLoginMapper::toDto))
}
