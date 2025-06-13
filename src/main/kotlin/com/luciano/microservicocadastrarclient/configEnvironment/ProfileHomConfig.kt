package com.luciano.microservicocadastrarclient.configEnvironment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ProfileHomConfig {

    @Bean
    @Profile("hom")
    fun homProfile(): String {
        return "Profile HOM"
    }

}