package com.luciano.microservicocadastrarclient.controller

import com.luciano.microservicocadastrarclient.dto.CreateClientUser
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.service.CadastreClient
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/clients")
class ClientAdressController(
    private val clientUserService: CadastreClient
) {

    @PostMapping
    fun createClientWithAddress(@RequestBody @Valid clientUser: ClientUser): CreateClientUser {
        val client = clientUserService.cadastreClient(clientUser)
        return CreateClientUser.fromEntity(client)
    }

}