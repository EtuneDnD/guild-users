package com.guild.users.infrastructure.jooq

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.domain.drivenports.PlayerPersistenceDrivenPort
import com.guild.users.domain.model.Player
import com.guild.users.infrastructure.jooq.mappers.PlayerMapper
import nu.studer.sample.Tables.PLAYERS
import org.jooq.DSLContext
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
internal class PlayerPersistenceJooqDrivenAdapter(
    var context: DSLContext,
    val playerMapper: PlayerMapper
) : PlayerPersistenceDrivenPort {
    fun get(): List<Any> {
        return context.selectFrom(PLAYERS).toList()
    }

    override fun savePlayer(createPlayerCommand: CreatePlayerCommand): Player =
        with(createPlayerCommand) {
            context.insertInto(PLAYERS, PLAYERS.USERNAME, PLAYERS.EMAIL, PLAYERS.PROFILE, PLAYERS.STRIKES)
                .values(userName, email, profileDescription, BigDecimal.valueOf(0))
                .returning()
                .fetchOne()
                ?.let(playerMapper::toDomain) ?: throw Exception()
        }

}