package com.luciano.microservicocadastrarclient.configEnvironment

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ProfileProdConfig {

    @Bean
    @Profile("prod")
    fun prodProfile(): String {
        return "Profile PROD"
    }

}
