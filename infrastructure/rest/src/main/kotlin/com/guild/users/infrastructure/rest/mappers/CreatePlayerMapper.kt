package com.guild.users.infrastructure.rest.mappers

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.infrastructure.rest.models.CreatePlayerDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
internal interface CreatePlayerMapper {
    @Mapping(target = "userId", ignore = true)
    fun toCommand(dto: CreatePlayerDto): CreatePlayerCommand
    fun toDto(command: CreatePlayerCommand): CreatePlayerDto
}