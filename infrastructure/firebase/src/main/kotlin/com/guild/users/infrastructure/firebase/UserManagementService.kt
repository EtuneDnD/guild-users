package com.guild.users.infrastructure.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
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

    fun createUser() {
        firebaseAuth.createUser(
            UserRecord.CreateRequest()
                .setEmail("")
                .setPassword("")
                .setEmailVerified(true)
        )
    }
}