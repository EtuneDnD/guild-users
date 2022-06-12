package com.guild.users.infrastructure.jooq

import com.guild.users.domain.commands.CreateDungeonMasterCommand
import com.guild.users.domain.drivenports.DungeonMasterPersistenceDrivenPort
import com.guild.users.domain.model.DungeonMaster
import com.guild.users.infrastructure.jooq.mappers.DungeonMasterMapper
import nu.studer.sample.Tables.DUNGEON_MASTERS
import nu.studer.sample.Tables.PLAYERS
import org.jooq.DSLContext
import org.springframework.stereotype.Service

@Service
internal class DungeonMasterPersistenceJooqDrivenAdapter(
    private var context: DSLContext,
    private val dungeonMasterMapper: DungeonMasterMapper,
) : DungeonMasterPersistenceDrivenPort {
    fun get(): List<Any> {
        return context.selectFrom(PLAYERS).toList()
    }

    override fun saveDungeonMaster(createDungeonMasterCommand: CreateDungeonMasterCommand): DungeonMaster =
        with(createDungeonMasterCommand) {
            context.insertInto(
                DUNGEON_MASTERS,
                DUNGEON_MASTERS.USERNAME,
                DUNGEON_MASTERS.EMAIL,
                DUNGEON_MASTERS.PROFILE,
                DUNGEON_MASTERS.RANK
            )
                .values(this.userName, this.email, this.profileDescription, this.rank.toString())
                .returning()
                .fetchOne()
                ?.let(dungeonMasterMapper::toDomain) ?: throw Exception()
        }
}
