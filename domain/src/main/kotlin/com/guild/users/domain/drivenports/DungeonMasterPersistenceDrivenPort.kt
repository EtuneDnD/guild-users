package com.guild.users.domain.drivenports

import com.guild.users.domain.commands.CreateDungeonMasterCommand
import com.guild.users.domain.model.DungeonMaster

interface DungeonMasterPersistenceDrivenPort {
    fun saveDungeonMaster(createDungeonMasterCommand: CreateDungeonMasterCommand): DungeonMaster
}