package com.guild.users.application.drivenports

import com.guild.users.application.commands.CreatePlayerCommand
import com.guild.users.domain.model.Player

interface WritePlayerDrivenPort {
    fun savePlayer(player: CreatePlayerCommand): Player
}