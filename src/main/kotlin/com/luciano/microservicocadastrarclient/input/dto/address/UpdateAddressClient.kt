package com.luciano.microservicocadastrarclient.input.dto.address

import com.luciano.microservicocadastrarclient.model.AddressGeneric

data class UpdateAddressClient(
    val numberResidence: String,
    val complement: String,
    val road: String
) {
    fun toEntity(existingAddress: AddressGeneric): AddressGeneric = existingAddress.copy(
        numberResidence = this.numberResidence,
        complement = this.complement,
        road = this.road
    )

    companion object {
        fun fromEntity(address: AddressGeneric): UpdateAddressClient? =
            address.complement?.let { complement ->
                address.numberResidence?.let { numberResidence ->
                    address.road?.let { road ->
                        UpdateAddressClient(numberResidence = numberResidence, complement = complement, road = road)
                    }
                }
            }
    }

}
