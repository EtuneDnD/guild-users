package com.guild.users.domain.drivenports

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.domain.model.Player

interface PlayerPersistenceDrivenPort {
    fun savePlayer(player: CreatePlayerCommand): Player
}