package com.guild.users.application.services

import com.guild.users.application.drivingports.WritePlayerDrivingPort
import com.guild.users.application.commands.CreatePlayerCommand
import com.guild.users.application.drivenports.WritePlayerDrivenPort
import com.guild.users.domain.model.Player
import org.springframework.stereotype.Service

@Service
class WritePlayerService(
    private val writePlayerDrivenPort: WritePlayerDrivenPort
) : WritePlayerDrivingPort {

    override fun savePlayer(createPlayerCommand: CreatePlayerCommand): Player =
        writePlayerDrivenPort.savePlayer(createPlayerCommand)
}