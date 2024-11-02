package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.model.AddressClient

interface AddressService {
    fun createAddress(idClient: Long, cepAddress: CepAddress): AddressClient
    fun getAllAddress(): List<AddressClient>

    fun getByIdAddress(idAddress: Long): AddressClient
    fun updateAddressClient(idClient: Long, idAddress: Long, updateAddressClient: AddressClient): AddressClient
}
