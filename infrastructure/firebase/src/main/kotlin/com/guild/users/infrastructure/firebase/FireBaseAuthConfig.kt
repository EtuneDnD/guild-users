package com.guild.users.infrastructure.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class FireBaseAuthConfig {
    @Value("classpath:service-account.json")
    private lateinit var serviceAccount: Resource

    @Bean
    fun firebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance(
            FirebaseApp.initializeApp(
                FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.inputStream))
                .build()
            )
        )


}