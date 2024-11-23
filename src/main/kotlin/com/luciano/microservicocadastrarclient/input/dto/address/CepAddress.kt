package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import java.util.UUID

class CepAddress(
    val idAddress: UUID? = null,
    val cep: String?,
    val numberResidence: String?,
    val client: ClientUser?
) {

    companion object {
        fun fromEntity(addressClient: AddressGeneric): CepAddress {
            return CepAddress(
                idAddress = addressClient.idAddress,
                cep = addressClient.cep,
                numberResidence = addressClient.numberResidence,
                client = addressClient.client
            )
        }
    }

}
