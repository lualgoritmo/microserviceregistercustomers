package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.model.AddressClient

interface AddressService {
    fun createAdress(idClient: Long, idAddress: Long): AddressClient
    fun getAllAddress(): List<AddressClient>

    fun getByIdAddress(idAddress: Long): AddressClient
}