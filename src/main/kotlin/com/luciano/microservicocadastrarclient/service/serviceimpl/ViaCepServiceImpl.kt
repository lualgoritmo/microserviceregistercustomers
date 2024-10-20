package com.luciano.microservicocadastrarclient.service.serviceimpl

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.microservicocadastrarclient.dto.AddressClientResponse
import com.luciano.microservicocadastrarclient.service.ViaCepService
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.WebTarget
import jakarta.ws.rs.core.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class ViaCepServiceImpl(
    @Autowired private val builder: Client,
    @Autowired private val objectMapper: ObjectMapper
) : ViaCepService {

    override fun getAddressByCep(cep: String): AddressClientResponse {
        try {
            val webTarget: WebTarget = builder.target("https://viacep.com.br/ws/$cep/json/")
            println("Requisitando endereço do CEP: $cep")

            // Faz a requisição para a API ViaCEP
            val responseJson = webTarget.request(MediaType.APPLICATION_JSON)
                .get(String::class.java)

            // Log para verificar a resposta recebida
            println("Resposta da API ViaCEP: $responseJson")

            // Verifica se o retorno contém um erro (ex.: "erro": true)
            if (responseJson.contains("\"erro\": true")) {
                throw IllegalArgumentException("CEP inválido ou não encontrado: $cep")
            }

            // Tentativa de conversão usando Jackson
            val addressResponse = objectMapper.readValue(responseJson, AddressClientResponse::class.java)

            // Verificar se o mapeamento está correto
            println("Endereço mapeado: $addressResponse")

            return addressResponse
        } catch (e: Exception) {
            println("Erro ao consultar o endereço via ViaCEP: ${e.message}")
            throw IllegalArgumentException("Falha ao consultar o CEP $cep: ${e.message}")
        }
    }
}