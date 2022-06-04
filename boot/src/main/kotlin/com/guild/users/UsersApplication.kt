package com.guild.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@SpringBootApplication
@EnableFeignClients
class UsersApplication

fun main(args: Array<String>) {
	try {
		runApplication<UsersApplication>(*args)
	} catch (e: Exception){
		e.printStackTrace()
	}
}
