package com.luciano.microservicocadastrarclient.service.serviceimpl

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.microservicocadastrarclient.input.controller.dto.AddressClientResponse
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

            val responseJson = webTarget.request(MediaType.APPLICATION_JSON)
                .get(String::class.java)

            val addressResponse = objectMapper.readValue(responseJson, AddressClientResponse::class.java)

            return addressResponse
        } catch (e: Exception) {
            throw IllegalArgumentException("Falha ao consultar o CEP $cep: ${e.message}")
        }
    }

}
