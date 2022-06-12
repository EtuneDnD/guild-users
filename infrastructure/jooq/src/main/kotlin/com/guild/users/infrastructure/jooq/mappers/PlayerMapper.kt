package com.guild.users.infrastructure.jooq.mappers

import com.guild.users.domain.model.Player
import nu.studer.sample.tables.records.PlayersRecord
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface PlayerMapper {
    @Mappings(
        value = [
            Mapping(target = "id", source = "userId"),
            Mapping(target = "userName", source = "username"),
            Mapping(target = "profileDescription", source = "profile"),
            Mapping(target = "strikes", source = "strikes"),
        ]
    )
    fun toDomain(playerRecord: PlayersRecord): Player
}
