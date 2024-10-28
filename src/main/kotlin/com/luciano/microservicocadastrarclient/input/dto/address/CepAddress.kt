package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser

class CepAddress(
    val idAddress: Long? = null,
    val cep: String?,
    val numberResidence: String?,
    val client: ClientUser?
) {

    companion object {
        fun fromEntity(addressClient: AddressClient): CepAddress {
            return CepAddress(
                idAddress = addressClient.idAddress,
                cep = addressClient.cep,
                numberResidence = addressClient.numberResidence,
                client = addressClient.client
            )
        }
    }

}