package com.guild.users.infrastructure.jooq

import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.application.drivenports.WritePlayerDrivenPort
import com.guild.users.domain.model.Player
import java.math.BigDecimal
import nu.studer.sample.Tables.PLAYERS
import nu.studer.sample.tables.records.PlayersRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Service

@Service
class Repo(
    var context: DSLContext
) : WritePlayerDrivenPort {
    fun get(): List<Any> {
        return context.selectFrom(PLAYERS).toList()
        return listOf<Any>()
    }

    override fun savePlayer(player: CreatePlayerCommand): Player {
        val x : PlayersRecord? = context.insertInto(PLAYERS, PLAYERS.USERNAME, PLAYERS.EMAIL, PLAYERS.PROFILE, PLAYERS.STRIKES)
            .values(player.userName, player.email, player.profileDescription, BigDecimal.valueOf(0))
            .returning()
            .fetchOne()

        return Player(id = ",", userName = "", email = "", profileDescription = "", 0)
    }
}