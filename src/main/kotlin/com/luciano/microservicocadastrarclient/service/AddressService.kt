package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import java.util.UUID

interface AddressService {
    fun createAddress(cepAddress: CepAddress, idClient: UUID?=null,idCollaborator:UUID ?=null): AddressGeneric
    fun getAllAddress(): List<AddressGeneric>

    fun getByIdAddress(idAddress: UUID): AddressGeneric
    fun updateAddressClient(idClient: UUID, idAddress: UUID, updateAddressClient: AddressGeneric): AddressGeneric
}
