package com.guild.users.infrastructure.firebase.adapters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import com.guild.users.domain.commands.AddRoleCommand
import com.guild.users.domain.commands.CreateUserCommand
import com.guild.users.domain.drivenports.UserManagementDrivenPort
import org.springframework.stereotype.Service

@Service
internal class UserManagementFirebaseDrivenAdapter(
    val firebaseAuth: FirebaseAuth
) : UserManagementDrivenPort {
    override fun createUser(createUserCommand: CreateUserCommand): Unit = with(createUserCommand) {
        firebaseAuth.createUser(
            UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password)
        )
    }

    override fun addRoleToUser(addRoleCommand: AddRoleCommand): Unit = with(addRoleCommand) {
        val map: Map<String, List<String>> = mapOf(
            "custom_claims" to roles.map { it.toString() }
        )
        firebaseAuth.setCustomUserClaims(id, map)
    }
}