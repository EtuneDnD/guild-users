package com.guild.users.infrastructure.rest

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
internal class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter())
    }

    fun jwtAuthenticationConverter(): JwtAuthenticationConverter? {
        val converter = JwtAuthenticationConverter()

        converter.setJwtGrantedAuthoritiesConverter { jwt: Jwt ->
            jwt.getClaimAsStringList("custom_claims")
                .map { role: String? -> SimpleGrantedAuthority(role) }
        }
        return converter
    }

}