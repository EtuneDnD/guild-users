package com.guild.users.application.services

import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.drivingports.UserManagementDrivingPort
import com.guild.users.domain.commands.CreatePlayerCommand
import com.guild.users.domain.commands.CreateUserCommand
import com.guild.users.domain.commands.GenerateLoginTokenCommand
import com.guild.users.domain.drivenports.AuthorizeUserDrivenPort
import com.guild.users.domain.drivenports.UserManagementDrivenPort
import com.guild.users.domain.drivenports.PersistPlayerDrivenPort
import com.guild.users.domain.model.Player
import org.springframework.stereotype.Service

@Service
class UserManagementDrivingAdapter(
    private val persistPlayerDrivenPort: PersistPlayerDrivenPort,
    private val authorizeUserDrivenPort: AuthorizeUserDrivenPort,
    private val userManagementDrivenPort: UserManagementDrivenPort
) : UserManagementDrivingPort {

    override fun authorizeUser(generateLoginTokenCommand: GenerateLoginTokenCommand) =
        authorizeUserDrivenPort.authorizeUser(generateLoginTokenCommand)

    override fun saveUser(createPlayerCommand: CreatePlayerCommand): Player = with(createPlayerCommand) {
        userManagementDrivenPort.createUser(CreateUserCommand(email, password))
        return persistPlayerDrivenPort.savePlayer(this)
    }

    override fun addRolesToUser(addRoleCommand: AddRoleCommand) {
        userManagementDrivenPort.addRoleToUser(addRoleCommand)
    }
}