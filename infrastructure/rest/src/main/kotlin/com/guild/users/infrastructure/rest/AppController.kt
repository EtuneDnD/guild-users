package com.guild.users.infrastructure.rest

import com.guild.users.application.drivingports.WritePlayerDrivingPort
import com.guild.users.infrastructure.firebase.FireBaseAuthConsumer
import com.guild.users.infrastructure.firebase.Permission
import com.guild.users.infrastructure.firebase.UserManagementService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
class AppController(
    val writePlayerDrivingPort: WritePlayerDrivingPort,
    val userManagementService: UserManagementService,
    val fireBaseAuthConsumer: FireBaseAuthConsumer,
) {
    @Secured("ROLE_ANONYMOUS")
    @GetMapping("/login")
    fun login(@RequestBody loginDto: LoginDto) : String = "hola"
        // fireBaseAuthConsumer.getToken(GetTokenDto(loginDto.userName, loginDto.password, true))

//    @Secured("ROLE_ANONYMOUS")
//    @PostMapping("/signup")
//    fun signup(@RequestBody createPlayerDto: CreatePlayerDto) {
//        writePlayerDrivingPort.savePlayer()
//    }

    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/admin/user-claims/{uid}")
    fun setUserClaims(
        @PathVariable uid: String,
        @RequestBody requestedClaims: List<Permission>
    ) {
        userManagementService.setUserClaims(uid, requestedClaims)
    }


}