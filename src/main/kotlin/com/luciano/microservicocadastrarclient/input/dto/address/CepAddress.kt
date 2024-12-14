package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.model.Collaborator
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
