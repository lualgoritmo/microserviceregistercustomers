package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import java.util.UUID

class CreateAddressCollaborator(
    val idCollaborator: UUID?,
    val nameCollaborator: String?,
    val idAddress: UUID? = null,
    val cep: String?,
    val road: String? = null,
    val city: String? = null,
    val numberResidence: String?,
    val complement: String?=null,
    val uf: String?=null
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
        fun fromEntity(address: AddressGeneric): CreateAddressCollaborator {
            return CreateAddressCollaborator(
                idCollaborator = address.collaborator?.idCollaborator,
                nameCollaborator = address.collaborator?.nameSurname,
                idAddress = address.idAddress,
                cep = address.cep,
                road = address.road,
                city = address.city,
                numberResidence = address.numberResidence,
                complement = address.complement,
                uf = address.uf
            )
        }
    }

}
