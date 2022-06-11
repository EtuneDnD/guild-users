package com.guild.users.domain.drivenports

import com.guild.users.domain.commands.CreateDungeonMasterCommand
import com.guild.users.domain.model.Player

interface DungeonMasterPersistenceDrivenPort {
    fun saveDungeonMaster(createDungeonMasterCommand: CreateDungeonMasterCommand): Player
}