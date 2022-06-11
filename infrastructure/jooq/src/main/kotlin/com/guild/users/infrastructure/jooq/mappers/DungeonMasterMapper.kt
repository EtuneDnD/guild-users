package com.guild.users.infrastructure.jooq.mappers

import com.guild.users.domain.model.DungeonMaster
import nu.studer.sample.tables.records.DungeonMastersRecord
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface DungeonMasterMapper {
    @Mappings(
        value = [
            Mapping(target = "id", source = "userId"),
            Mapping(target = "userName", source = "username"),
            Mapping(target = "profileDescription", source = "profile"),
            Mapping(target = "rank", source = "rank"),
        ]
    )
    fun toDomain(dungeonMastersRecord: DungeonMastersRecord): DungeonMaster
}