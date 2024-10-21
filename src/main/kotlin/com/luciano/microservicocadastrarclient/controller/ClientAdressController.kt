package com.luciano.microservicocadastrarclient.controller

import com.luciano.microservicocadastrarclient.dto.CreateClientUser
import com.luciano.microservicocadastrarclient.dto.ClientWithAddress
import com.luciano.microservicocadastrarclient.dto.UpdateClient
import com.luciano.microservicocadastrarclient.service.CadastreClient
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clients")
class ClientAdressController(
    private val clientUserService: CadastreClient
) {
    @PostMapping("/createclient")
    fun createClientWithAddress(
        @RequestBody @Valid clientUser: CreateClientUser,
        numberResidence: String? = null
    ): CreateClientUser {
        val client =
            clientUserService.cadastreClient(clientUser.toEntity())
        return CreateClientUser.fromEntity(client)
    }
    @GetMapping("/allclients")
    fun createClientWithAddress(): List<ClientWithAddress> = clientUserService.getAllListClients().map {
        ClientWithAddress.fromEntity(it)
    }

    @GetMapping("/{idClient}")
    fun getByIdClient(@PathVariable("idClient") idClient: Long): ClientWithAddress {
        val client = clientUserService.getClientById(idClient)
        return ClientWithAddress.fromEntity(client)
    }

    @PutMapping("/update/{idClient}")
    fun updateClient(
        @PathVariable("idClient") idClient:Long,
        @RequestBody @Valid client: UpdateClient
    ): ResponseEntity<UpdateClient>{
        val updateClient = clientUserService.updateClientUser(idClient, client)
        return ResponseEntity.ok(UpdateClient.fromEntity(updateClient))
    }

}