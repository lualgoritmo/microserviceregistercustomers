package com.luciano.microservicocadastrarclient.input.dto.client

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class CreateClientUser(
    val idClient: UUID? = null,
    val nameSurname: String,
    val cpf: String,
    val cep: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val dateOfBirth: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val numberResidence: String?,
    val phone: String,
    val rg: String,
    @Email
    val email: String,
    var addressClient: Set<AddressClient> = emptySet()
) {
    fun toEntity() = ClientUser(
        idClientUser = this.idClient,
        nameSurname = this.nameSurname,
        cpf = this.cpf,
        cep = this.cep,
        dateOfBirth = this.dateOfBirth,
        phone = this.phone,
        numberResidence = this.numberResidence,
        rg = this.rg,
        email = this.email,
        addressClient = mutableSetOf()
    )

    companion object {
        fun fromEntity(clientUser: ClientUser) = CreateClientUser(
            idClient = clientUser.idClientUser,
            nameSurname = clientUser.nameSurname,
            cpf = clientUser.cpf,
            cep = clientUser.cep,
            numberResidence = clientUser.numberResidence,
            dateOfBirth = clientUser.dateOfBirth,
            phone = clientUser.phone,
            rg = clientUser.rg,
            email = clientUser.email,
            addressClient = clientUser.addressClient
        )
    }

}
