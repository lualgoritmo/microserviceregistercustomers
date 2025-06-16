package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.entity.AddressGeneric
import com.luciano.microservicocadastrarclient.entity.ClientUser
import com.luciano.microservicocadastrarclient.entity.Collaborator
import java.util.UUID

data class CepAddress(
    val idAddress: UUID? = null,
    val cep: String?,
    val numberResidence: String?,
    val client: ClientUser?=null,
    val collaborator: Collaborator?=null
) {
    companion object {
        fun fromEntity(addressGeneric: AddressGeneric): CepAddress {
            return CepAddress(
                idAddress = addressGeneric.idAddress,
                cep = addressGeneric.cep,
                numberResidence = addressGeneric.numberResidence,
                client = addressGeneric.client,
                collaborator = addressGeneric.collaborator
            )
        }
    }

}
