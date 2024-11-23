package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import java.util.UUID

class CreateAddress(
    val idAddress: UUID? = null,
    val cep: String?,
    val road: String? = null,
    val city: String? = null,
    val numberResidence: String?,
    val complement: String?=null,
    val uf: String?=null,
    val client: ClientUser?
) {
    fun toEntity(): AddressGeneric = AddressGeneric(
        idAddress = this.idAddress,
        cep = this.cep,
        road = this.road,
        city = this.city,
        numberResidence = this.numberResidence,
        complement = this.complement,
        uf = this.uf,
        client = this.client,
        null
    )

    companion object {
        fun fromEntity(addressClient: AddressGeneric): CreateAddress {
            return CreateAddress(
                idAddress = addressClient.idAddress,
                cep = addressClient.cep,
                road = addressClient.road,
                city = addressClient.city,
                numberResidence = addressClient.numberResidence,
                complement = addressClient.complement,
                uf = addressClient.uf,
                client = addressClient.client
            )
        }
    }

}
