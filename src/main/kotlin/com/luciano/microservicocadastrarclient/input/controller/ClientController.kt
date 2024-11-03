package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.client.ClientWithAddress
import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/clients")
class ClientController(
    private val clientUserService: CadastreClient
) {
    @PostMapping("/create-client")
    fun getAllListClients(
        @RequestBody @Valid clientUser: CreateClientUser
    ): ResponseEntity<CreateClientUser> {
        val client =
            clientUserService.cadastreClient(clientUser.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateClientUser.fromEntity(client))
    }

    @GetMapping("/allclients")
    fun getAllListClients(): List<ClientWithAddress> = clientUserService.getAllListClients().map {
        ClientWithAddress.fromEntity(it)
    }

    @GetMapping("/{idClient}")
    fun getByIdClient(@PathVariable("idClient") idClient: UUID): ResponseEntity<ClientWithAddress> {
        val client = clientUserService.getClientById(idClient)
        return ResponseEntity.status(HttpStatus.OK).body(ClientWithAddress.fromEntity(client))
    }

    @PutMapping("/update/{idClient}")
    fun updateClient(
        @PathVariable("idClient") idClient: UUID,
        @RequestBody @Valid client: UpdateClient
    ): ResponseEntity<UpdateClient> {
        val updateClient = clientUserService.updateClientUser(idClient, client)
        return ResponseEntity.ok(UpdateClient.fromEntity(updateClient))
    }

}