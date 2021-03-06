package com.guild.users.application.services

import com.guild.users.domain.commands.*
import com.guild.users.domain.drivenports.AuthorizeUserDrivenPort
import com.guild.users.domain.drivenports.DungeonMasterPersistenceDrivenPort
import com.guild.users.domain.drivenports.PlayerPersistenceDrivenPort
import com.guild.users.domain.drivenports.UserManagementDrivenPort
import com.guild.users.domain.drivingports.UserManagementDrivingPort
import com.guild.users.domain.model.Role
import com.guild.users.domain.model.User
import org.springframework.stereotype.Service

@Service
class UserManagementDrivingAdapter(
    private val playerPersistenceDrivenPort: PlayerPersistenceDrivenPort,
    private val dungeonMasterPersistenceDrivenPort: DungeonMasterPersistenceDrivenPort,
    private val authorizeUserDrivenPort: AuthorizeUserDrivenPort,
    private val userManagementDrivenPort: UserManagementDrivenPort
) : UserManagementDrivingPort {

    override fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand) =
        authorizeUserDrivenPort.authorizeUser(generateLoginTokenCommand)

    override fun saveUser(createUserAbstractCommand: CreateUserAbstractCommand): User =
        with(createUserAbstractCommand) {
            val uuid: String = userManagementDrivenPort.createUser(CreateBaseUserCommand(email, password))
            createUserAbstractCommand.userId = uuid

            return when (createUserAbstractCommand.role) {
                Role.PLAYER ->
                    playerPersistenceDrivenPort.savePlayer(this as CreatePlayerCommand)
                Role.DUNGEON_MASTER ->
                    dungeonMasterPersistenceDrivenPort.saveDungeonMaster(this as CreateDungeonMasterCommand)
            }
        }

    override fun addRolesToUser(addRoleCommand: AddRoleCommand) {
        userManagementDrivenPort.addRoleToUser(addRoleCommand)
    }
}