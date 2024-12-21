package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import java.util.UUID

interface CadastreClient {
    fun cadastreClient(client: ClientUser): ClientUser
    fun getClientById(idClient: UUID): ClientUser
    fun getAllListClients(): List<ClientUser>
    fun updateClientUser(idClient: UUID, client: UpdateClient): ClientUser
    fun deleteClientId(idClient: UUID)
}