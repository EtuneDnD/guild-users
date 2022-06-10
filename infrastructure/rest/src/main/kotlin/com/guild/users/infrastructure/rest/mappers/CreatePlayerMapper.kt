package com.guild.users.infrastructure.rest.mappers

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.infrastructure.rest.models.CreatePlayerDto
import org.mapstruct.Mapper

@Mapper
internal interface CreatePlayerMapper {
    fun toCommand(dto: CreatePlayerDto): CreatePlayerCommand
    fun toDto(command: CreatePlayerCommand): CreatePlayerDto
}