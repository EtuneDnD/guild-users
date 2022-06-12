package com.guild.users.infrastructure.rest.mappers

import com.guild.users.domain.commands.GenerateLoginTokenCommand
import com.guild.users.infrastructure.rest.models.LoginDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
internal interface GenerateLoginTokenMapper {
    fun toCommand(dto: LoginDto): GenerateLoginTokenCommand
}