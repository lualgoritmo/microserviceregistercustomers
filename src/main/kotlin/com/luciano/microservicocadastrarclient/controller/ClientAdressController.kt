package com.luciano.microservicocadastrarclient.controller

import com.luciano.microservicocadastrarclient.dto.CreateClientUser
import com.luciano.microservicocadastrarclient.dto.UpdateClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.service.CadastreClient
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
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
    fun createClientWithAddress(): List<CreateClientUser> = clientUserService.getAllListClients().map {
        CreateClientUser.fromEntity(it)
    }

    @GetMapping("/{idClient}")
    fun getByIdClient(@PathVariable("idClient") idClient: Long): ClientUser {
        return clientUserService.getClientById(idClient)
    }

    @PutMapping("/update/{idClient}")
    fun updateClient(
        @PathVariable("idClient") idClient:Long,
        @RequestBody @Valid client: UpdateClient
    ): ResponseEntity<UpdateClient>{
        val updateClient = clientUserService.updateClientUser(idClient, client)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(UpdateClient.fromEntity(updateClient))
    }

}