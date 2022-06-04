package com.guild.users.domain.model

data class Player(
    override var id: String,
    override var userName: String,
    override var email: String,
    override var profileDescription: String,
    val strikes: Int
) : User()
