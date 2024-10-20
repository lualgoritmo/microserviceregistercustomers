package com.luciano.microservicocadastrarclient.controller

import com.luciano.microservicocadastrarclient.dto.CreateClientUser
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.service.CadastreClient
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clients")
class ClientAdressController(
    private val clientUserService: CadastreClient
) {
    @PostMapping("/createclient")
    fun createClientWithAddress(
        @RequestBody @Valid clientUser: ClientUser,
        numberResidence: String? = null
    ): CreateClientUser {
        val client = clientUserService.cadastreClient(clientUser)
        return CreateClientUser.fromEntity(client)
    }
    @GetMapping("/allclients")
    fun createClientWithAddress(): List<ClientUser> = clientUserService.getAllListClients()


}