package com.luciano.microservicocadastrarclient

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

    fun main() {
        val passwordEncoder = BCryptPasswordEncoder()
        val rawPassword = "123456"
        val encodedPassword = passwordEncoder.encode(rawPassword)
        println("Senha codificada: $encodedPassword")
    }

