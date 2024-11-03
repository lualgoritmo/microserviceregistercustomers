package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.model.AddressClient
import java.util.UUID

interface AddressService {
    fun createAddress(idClient: UUID, cepAddress: CepAddress): AddressClient
    fun getAllAddress(): List<AddressClient>

    fun getByIdAddress(idAddress: UUID): AddressClient
    fun updateAddressClient(idClient: UUID, idAddress: UUID, updateAddressClient: AddressClient): AddressClient
}
