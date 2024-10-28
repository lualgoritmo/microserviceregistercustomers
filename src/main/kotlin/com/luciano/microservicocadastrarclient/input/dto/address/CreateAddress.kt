package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser

class CreateAddress(
    val idAddress: Long? = null,
    val cep: String?,
    val road: String? = null,
    val city: String? = null,
    val numberResidence: String?,
    val complement: String?=null,
    val uf: String?=null,
    val client: ClientUser?
) {
    fun toEntity(): AddressClient = AddressClient(
        idAddress = this.idAddress,
        cep = this.cep,
        road = this.road,
        city = this.city,
        numberResidence = this.numberResidence,
        complement = this.complement,
        uf = this.uf,
        client = this.client
    )

    companion object {
        fun fromEntity(addressClient: AddressClient): CreateAddress {
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
