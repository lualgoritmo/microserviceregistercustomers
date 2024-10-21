package com.luciano.microservicocadastrarclient.dto

import com.luciano.microservicocadastrarclient.model.ClientUser
import jakarta.validation.constraints.Email

data class UpdateClient(
    val cep: String,
    val phone: String,
    val numberResidence: String,
    @Email
    val email: String
) {
    fun toEntity(existingClient: ClientUser): ClientUser = existingClient.copy(
        cep = this.cep,
        phone = this.phone,
        numberResidence = this.numberResidence,
        email = this.email
    )

    companion object {
        fun fromEntity(clientUser: ClientUser) = UpdateClient(
            cep = clientUser.cep,
            phone = clientUser.phone,
            numberResidence = clientUser.rg,
            email = clientUser.email,
        )
    }
}
