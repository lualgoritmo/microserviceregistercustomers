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

    fun getAddressClient(cep: String, client: ClientUser, numberResidence:String): AddressClient {
        val addressResponse = this.getAddressByCep(cep)

        if (addressResponse.cep.isNullOrEmpty() || addressResponse.logradouro.isNullOrEmpty()) {
            throw IllegalArgumentException("Dados de endereço inválidos retornados para o CEP $cep")
        }

        return AddressClient(
            cep = addressResponse.cep,
            road = addressResponse.logradouro,
            city = addressResponse.localidade ?: "Cidade não informada",
           numberResidence = numberResidence,
            complement = addressResponse.complemento ?: "",
            uf = addressResponse.uf ?: "UF não informada",
            client = client
        )
    }

}
