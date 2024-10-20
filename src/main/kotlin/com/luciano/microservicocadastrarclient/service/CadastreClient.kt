package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.model.ClientUser

interface CadastreClient {
    fun cadastreClient(client: ClientUser): ClientUser
    fun getClientById(idClient: Long): ClientUser
    fun getAllListClients(): List<ClientUser>
    fun updateClientUser(idClient: Long, client: ClientUser): ClientUser
    fun deleteClientId(idClient: Long)
}