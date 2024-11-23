package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import java.util.UUID

interface AddressService {
    fun createAddress(idClient: UUID, cepAddress: CepAddress): AddressGeneric
    fun getAllAddress(): List<AddressGeneric>

    fun getByIdAddress(idAddress: UUID): AddressGeneric
    fun updateAddressClient(idClient: UUID, idAddress: UUID, updateAddressClient: AddressGeneric): AddressGeneric
}
