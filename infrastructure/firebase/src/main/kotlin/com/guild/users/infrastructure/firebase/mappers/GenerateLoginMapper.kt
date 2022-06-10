package com.guild.users.infrastructure.firebase.mappers

import com.guild.users.domain.commands.GenerateLoginTokenCommand
import com.guild.users.infrastructure.firebase.rest.models.GetTokenDto
import org.mapstruct.Mapper

@Mapper
internal interface GenerateLoginMapper {
    fun toDto(command: GenerateLoginTokenCommand): GetTokenDto
}