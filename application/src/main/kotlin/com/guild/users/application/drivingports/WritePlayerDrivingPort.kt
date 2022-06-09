package com.guild.users.application.drivingports

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.domain.model.Player

interface WritePlayerDrivingPort {
    fun savePlayer(createPlayerCommand: CreatePlayerCommand) : Player
}