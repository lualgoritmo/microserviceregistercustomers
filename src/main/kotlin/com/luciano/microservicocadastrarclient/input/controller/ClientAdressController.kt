package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.input.dto.client.ClientWithAddress
import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.service.serviceimpl.CadastreClient
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequestMapping("/v1/clients")
class ClientAdressController(
    private val clientUserService: CadastreClient
) {
    @PostMapping("/createclient")
    fun createClientWithAddress(
        @RequestBody @Valid clientUser: CreateClientUser
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