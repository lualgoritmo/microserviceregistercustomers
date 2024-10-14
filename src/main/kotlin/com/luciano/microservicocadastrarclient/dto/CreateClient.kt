package com.luciano.microservicocadastrarclient.dto

import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime

data class CreateClientUser(

    val nameSurname: String,
    val cpf: String,
    val cep: String,
    val dateOfBirth: LocalDate,
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val phone: String,
    val rg: String,
    @Email
    val email: String,
    var addressClient: Set<AddressClient>
) {
    fun toEntity() = ClientUser(
        nameSurname = this.nameSurname,
        cpf = this.cpf,
        cep = this.cep,
        dateOfBirth = this.dateOfBirth,
        phone = this.phone,
        rg = this.rg,
        email = this.email,
        addressClient = this.addressClient
    )

    companion object {
        fun fromEntity(clientUser: ClientUser) = CreateClientUser(
            nameSurname = clientUser.nameSurname,
            cpf = clientUser.cpf,
            cep = clientUser.cep,
            dateOfBirth = clientUser.dateOfBirth,
            phone = clientUser.phone,
            rg = clientUser.rg,
            email = clientUser.email,
            addressClient = clientUser.addressClient
        )
    }

}
