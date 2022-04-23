package com.guild.users

import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
class AppController(
    val userManagementService: UserManagementService
) {

    @GetMapping("/test")
    fun test(principal: Principal): String = principal.name

    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/admin/user-claims/{uid}")
    fun setUserClaims(
        @PathVariable uid: String,
        @RequestBody requestedClaims: List<Permission>
    ) {
        userManagementService.setUserClaims(uid, requestedClaims)
    }

}