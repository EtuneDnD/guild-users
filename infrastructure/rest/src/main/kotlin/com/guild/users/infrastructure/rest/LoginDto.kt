package com.guild.users.infrastructure.rest

import com.fasterxml.jackson.annotation.JsonProperty


data class LoginDto(
    @JsonProperty("user-name")
    val userName: String,
    val password: String
)
