package com.luciano.microservicocadastrarclient.configEnvironment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ProfileDevConfig {

    @Bean
    @Profile("dev")
    fun devProfile(): String {
        return "Profile DEV"
    }

}