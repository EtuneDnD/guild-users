package com.guild.users.infrastructure.rest.api

import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.drivingports.UserManagementDrivingPort
import com.guild.users.infrastructure.rest.mappers.CreatePlayerMapper
import com.guild.users.infrastructure.rest.mappers.GenerateLoginTokenMapper
import com.guild.users.infrastructure.rest.mappers.RoleMapper
import com.guild.users.infrastructure.rest.models.CreatePlayerDto
import com.guild.users.infrastructure.rest.models.LoginDto
import com.guild.users.infrastructure.rest.models.RoleDto
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
internal class UserController(
    private val userManagementDrivingPort: UserManagementDrivingPort,
    private val createPlayerMapper: CreatePlayerMapper,
    private val generateLoginTokenMapper: GenerateLoginTokenMapper,
    private val roleMapper: RoleMapper
) {
    @Secured("ROLE_ANONYMOUS")
    @GetMapping("/login")
    fun login(@RequestBody loginDto: LoginDto) : String =
        userManagementDrivingPort.authorizeUser(loginDto.let(generateLoginTokenMapper::toCommand))

    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/signup")
    fun signup(@RequestBody createPlayerDto: CreatePlayerDto) =
        userManagementDrivingPort.saveUser(createPlayerDto.let(createPlayerMapper::toCommand))

    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/admin/user-claims/{uid}")
    fun setUserClaims(
        @PathVariable uid: String,
        @RequestBody requestedClaims: List<RoleDto>
    ) {
        userManagementDrivingPort.addRolesToUser(
            AddRoleCommand(
                id = uid,
                roles = requestedClaims.map(roleMapper::toDomain)
            )
        )
    }
}
