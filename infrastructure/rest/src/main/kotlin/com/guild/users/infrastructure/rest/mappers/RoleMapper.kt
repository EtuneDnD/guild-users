package com.guild.users.infrastructure.rest.mappers

import com.guild.users.domain.model.Role
import com.guild.users.infrastructure.rest.models.RoleDto
import org.mapstruct.Mapper

@Mapper
internal interface RoleMapper {
    fun toDomain(role: RoleDto): Role
}