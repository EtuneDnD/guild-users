package com.guild.users.infrastructure.firebase.rest.models

internal data class GetTokenDto(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)