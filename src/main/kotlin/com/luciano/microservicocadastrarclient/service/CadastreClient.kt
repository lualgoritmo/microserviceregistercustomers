package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.input.controller.dto.UpdateClient
import com.luciano.microservicocadastrarclient.model.ClientUser

interface CadastreClient {
    fun cadastreClient(client: ClientUser): ClientUser
    fun getClientById(idClient: Long): ClientUser
    fun getAllListClients(): List<ClientUser>
    fun updateClientUser(idClient: Long, client: UpdateClient): ClientUser
    fun deleteClientId(idClient: Long)
}