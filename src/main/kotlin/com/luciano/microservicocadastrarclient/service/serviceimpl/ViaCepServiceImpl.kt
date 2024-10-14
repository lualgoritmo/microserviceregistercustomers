package com.luciano.microservicocadastrarclient.service.serviceimpl

import com.luciano.microservicocadastrarclient.dto.AddressClientResponse
import com.luciano.microservicocadastrarclient.service.ViaCepService
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.WebTarget
import jakarta.ws.rs.core.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ViaCepServiceImpl(
    @Autowired
    private val builder: Client
): ViaCepService {

    override fun getAddressByCep(cep: String): AddressClientResponse {
        val webTarget: WebTarget = builder.target("https://viacep.com.br/ws/$cep/json/")
        return webTarget.request(MediaType.APPLICATION_JSON)
            .get(AddressClientResponse::class.java)
    }

}
