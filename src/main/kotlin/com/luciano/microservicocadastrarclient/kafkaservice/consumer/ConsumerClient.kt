package com.luciano.microservicocadastrarclient.kafkaservice.consumer

interface ConsumerClient {
    fun createClient(message: String)
}