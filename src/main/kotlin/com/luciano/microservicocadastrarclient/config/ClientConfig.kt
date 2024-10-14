package com.luciano.microservicocadastrarclient.config

import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {
    @Bean
    fun client(): Client = ClientBuilder.newClient()
}