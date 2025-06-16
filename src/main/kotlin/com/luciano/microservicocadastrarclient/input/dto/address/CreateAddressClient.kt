package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.entity.AddressGeneric
import java.util.UUID

class CreateAddressClient(
    val idAddress: UUID? = null,
    val idClient: UUID?,
    val nameClient: String?,
    val cep: String?,
    val road: String? = null,
    val city: String? = null,
    val numberResidence: String?,
    val complement: String?=null,
    val uf: String?=null,
) {
    fun toEntity(): AddressGeneric = AddressGeneric(
        idAddress = this.idAddress,
        cep = this.cep,
        road = this.road,
        city = this.city,
        numberResidence = this.numberResidence,
        complement = this.complement,
        uf = this.uf
    )
    companion object {
        fun fromEntity(addressClient: AddressGeneric): CreateAddressClient {
            return CreateAddressClient(
                idAddress = addressClient.idAddress,
                idClient = addressClient.client?.idClientUser,
                nameClient = addressClient.client?.nameSurname,
                cep = addressClient.cep,
                road = addressClient.road,
                city = addressClient.city,
                numberResidence = addressClient.numberResidence,
                complement = addressClient.complement,
                uf = addressClient.uf
            )
        }
    }

}
