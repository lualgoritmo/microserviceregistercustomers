package com.luciano.microservicocadastrarclient.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime

data class CreateClientUser(

    val nameSurname: String,
    val cpf: String,
    val cep: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val dateOfBirth: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val numberResidence:String? = null,
    val phone: String,
    val rg: String,
    @Email
    val email: String,
    var addressClient: Set<AddressClient> = emptySet()
) {
    fun toEntity() = ClientUser(
        nameSurname = this.nameSurname,
        cpf = this.cpf,
        cep = this.cep,
        dateOfBirth = this.dateOfBirth,
        registrationDate = this.registrationDate,
        phone = this.phone,
        rg = this.rg,
        email = this.email,
        addressClient = mutableSetOf()
    )

    companion object {
        fun fromEntity(clientUser: ClientUser) = CreateClientUser(
            nameSurname = clientUser.nameSurname,
            cpf = clientUser.cpf,
            cep = clientUser.cep,
            numberResidence = clientUser.numberResidence,
            dateOfBirth = clientUser.dateOfBirth,
            registrationDate = clientUser.registrationDate,
            phone = clientUser.phone,
            rg = clientUser.rg,
            email = clientUser.email,
            addressClient = clientUser.addressClient
        )
    }

}
