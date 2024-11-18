package com.luciano.microservicocadastrarclient.kafkaservice.consumerImpl

import com.luciano.microservicocadastrarclient.kafkaservice.consumer.ConsumerClient
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ConsumerClientImpl : ConsumerClient {

    @KafkaListener(topics = ["create-client-topic"], groupId = "group-1")
    override fun createClient(message: String) {
        TODO("Not yet implemented")
    }

}
