package com.luciano.microservicocadastrarclient.input.dto.collaborator

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.Collaborator
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class CreateCollaborator(
    val idCollaborator: UUID? = null,
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
    val addressClient: Set<AddressGeneric>? = null
) {
    fun toEntity() = Collaborator(
        idCollaborator = this.idCollaborator,
        nameSurname = this.nameSurname,
        cpf = this.cpf,
        cep = this.cep,
        dateOfBirth = this.dateOfBirth,
        phone = this.phone,
        numberResidence = this.numberResidence,
        rg = this.rg,
        email = this.email,
        addressCollaborator = mutableSetOf()
    )
    companion object {
        fun fromEntity(collaborator: Collaborator) = CreateCollaborator(
            idCollaborator = collaborator.idCollaborator,
            nameSurname = collaborator.nameSurname,
            cpf = collaborator.cpf,
            cep = collaborator.cep,
            dateOfBirth = collaborator.dateOfBirth,
            phone = collaborator.phone,
            numberResidence = collaborator.numberResidence,
            rg = collaborator.rg,
            email = collaborator.email,
            addressClient = collaborator.addressCollaborator.map { address ->
                AddressGeneric(
                    idAddress = address.idAddress,
                    cep = address.cep,
                    road = address.road,
                    city = address.city,
                    numberResidence = address.numberResidence,
                    complement = address.complement,
                    uf = address.uf
                )
            }.toSet()
        )

        fun fromListCollaborators(collaborators: List<Collaborator>) =
            collaborators.map { collaborator ->
                CreateCollaborator(
                    idCollaborator = collaborator.idCollaborator,
                    nameSurname = collaborator.nameSurname,
                    cpf = collaborator.cpf,
                    cep = collaborator.cep,
                    dateOfBirth = collaborator.dateOfBirth,
                    phone = collaborator.phone,
                    numberResidence = collaborator.numberResidence,
                    rg = collaborator.rg,
                    email = collaborator.email,
                    addressClient = mutableSetOf()
                )
            }

    }
}
