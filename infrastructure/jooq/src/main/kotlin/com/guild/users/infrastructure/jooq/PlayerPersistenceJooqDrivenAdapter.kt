package com.guild.users.infrastructure.jooq

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.domain.drivenports.PlayerPersistenceDrivenPort
import com.guild.users.domain.model.Player
import java.math.BigDecimal
import nu.studer.sample.Tables.PLAYERS
import nu.studer.sample.tables.records.PlayersRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Service

@Service
internal class PlayerPersistenceJooqDrivenAdapter(
    var context: DSLContext
) : PlayerPersistenceDrivenPort {
    fun get(): List<Any> {
        return context.selectFrom(PLAYERS).toList()
    }

    override fun savePlayer(player: CreatePlayerCommand): Player {
        val x : PlayersRecord? = context.insertInto(PLAYERS, PLAYERS.USERNAME, PLAYERS.EMAIL, PLAYERS.PROFILE, PLAYERS.STRIKES)
            .values(player.userName, player.email, player.profileDescription, BigDecimal.valueOf(0))
            .returning()
            .fetchOne()

        return Player(id = ",", userName = "", email = "", profileDescription = "", 0)
    }
}