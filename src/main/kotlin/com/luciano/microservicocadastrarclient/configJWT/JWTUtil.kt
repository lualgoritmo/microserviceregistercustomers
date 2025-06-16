package com.luciano.microservicocadastrarclient.configJWT

import com.luciano.microservicocadastrarclient.output.gateway.CollaboratorServiceImpl
import com.luciano.microservicocadastrarclient.service.CollaboratorService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil(private val collaboratorService:CollaboratorServiceImpl) {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private var expiration: Long = 120000

    fun generateToken(userName: String, authorities: MutableCollection<out GrantedAuthority>): String? {
         val key:SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

        return Jwts.builder()
            .setSubject(userName)
            .claim("role", authorities.map { it.authority })
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact()
    }

    fun isValid(jwtToken: String?): Boolean {
        if (jwtToken.isNullOrBlank()) return false
        return try {
            Jwts
                .parserBuilder()
                .setSigningKey(secret.toByteArray())
                .build()
                .parseClaimsJws(jwtToken)
            true
        } catch (e: Exception) { // pega qualquer exceção!
            false
        }
    }


    fun getAuthentication(jwt: String?): Authentication {
        val userName =
            Jwts
                .parserBuilder()
                .setSigningKey(secret.toByteArray())
                .build().parseClaimsJws(jwt)
                .body.subject
        val user = collaboratorService.loadUserByUsername(userName)
        return UsernamePasswordAuthenticationToken(userName, null, user.authorities)
    }
}