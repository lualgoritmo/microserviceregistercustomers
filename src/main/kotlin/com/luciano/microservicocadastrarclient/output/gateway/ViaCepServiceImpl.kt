package com.luciano.microservicocadastrarclient.output.gateway

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.service.dto.AddressClientResponse
import com.luciano.microservicocadastrarclient.service.service.ViaCepService
import jakarta.ws.rs.ProcessingException
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

    companion object {
        private const val VIA_CEP_URL = "https://viacep.com.br/ws/"
    }

    override fun getAddressByCep(cep: String): AddressClientResponse {
        //validateCep(cep)

        return try {
            val webTarget: WebTarget = builder.target("$VIA_CEP_URL$cep/json/")
            val responseJson = webTarget.request(MediaType.APPLICATION_JSON).get(String::class.java)
            objectMapper.readValue(responseJson, AddressClientResponse::class.java)
        } catch (e: ProcessingException) {
            throw IllegalArgumentException("Erro de comunicação ao consultar o CEP $cep: ${e.message}", e)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException("Erro ao processar a resposta do CEP $cep: ${e.message}", e)
        }
    }

//    private fun validateCep(cep: String) {
//        if (!cep.matches(Regex("^[0-9]{5}-?[0-9]{3}\$"))) {
//            throw IllegalArgumentException("CEP inválido: $cep")
//        }
//    }
    fun getAddressClient(client: ClientUser): AddressClient {
        val addressResponse = this.getAddressByCep(client.cep)

        return AddressClient(
            cep = addressResponse.cep,
            road = addressResponse.logradouro ?: "Logradouro não informado",
            city = addressResponse.localidade,
            numberResidence = client.numberResidence ?: "",
            complement = addressResponse.complemento ?: "",
            uf = addressResponse.uf ?: "UF não informada",
            client = client
        )
    }
}

