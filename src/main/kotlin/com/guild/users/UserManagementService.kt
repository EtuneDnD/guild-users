package com.guild.users

import com.google.firebase.auth.FirebaseAuth
import org.springframework.stereotype.Service

@Service
class UserManagementService(
    val firebaseAuth: FirebaseAuth
) {
    fun setUserClaims(uuid: String, requestedPermissions: List<Permission>) {
        val map: Map<String, List<String>> = mapOf(
            "custom_claims" to requestedPermissions.map { it.toString() }
        )
        firebaseAuth.setCustomUserClaims(uuid, map)
    }
}