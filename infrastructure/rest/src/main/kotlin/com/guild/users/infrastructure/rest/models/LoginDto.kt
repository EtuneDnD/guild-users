package com.guild.users.infrastructure.rest.models

import com.fasterxml.jackson.annotation.JsonProperty


internal data class LoginDto(
    @JsonProperty("user-name")
    val userName: String,
    val password: String
)
