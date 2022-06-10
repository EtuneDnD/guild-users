package com.guild.users.domain.commands

import com.guild.users.domain.model.Role

data class AddRoleCommand(
    val id: String,
    val roles: List<Role>,
)
