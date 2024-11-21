package com.luciano.microservicocadastrarclient.kafkaservice.consumerImpl

import com.luciano.microservicocadastrarclient.kafkaservice.consumer.ConsumerClient
import org.springframework.stereotype.Service

@Service
class ConsumerClientImpl : ConsumerClient {

    //    @KafkaListener(topics = ["client-create-topic"], groupId = "client-service-group")
//    override fun createClient(message: String) {
//        println("Cliente criado no outro serviço com sucesso:$message")
//    }
    override fun createClient(message: String) {
        TODO("Not yet implemented")
    }

}

