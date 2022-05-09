package com.guild.users

import com.fasterxml.jackson.annotation.JsonProperty


data class LoginDto(
    @JsonProperty("user-name")
    val userName: String,
    val password: String
)
