package com.guild.users

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

// TODO add api key in a secure way
@FeignClient(name = "dummy", url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=")
interface FireBaseAuthConsumer {
    @PostMapping
    fun getToken(@RequestBody getTokenDto: GetTokenDto): String
}