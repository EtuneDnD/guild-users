package com.guild.users.infrastructure.rest.models

import com.guild.users.domain.model.Role

internal data class CreatePlayerDto(
    val userName: String,
    val email: String,
    val password: String,
    val role: Role = Role.PLAYER,
    val profileDescription: String?,
    val strikes: Int,
)