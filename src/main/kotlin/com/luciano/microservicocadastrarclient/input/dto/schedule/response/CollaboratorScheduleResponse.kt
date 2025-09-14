package com.luciano.microservicocadastrarclient.input.dto.shedule.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.model.Collaborator
import jakarta.persistence.Column
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class CollaboratorScheduleResponse(
    val idCollaborator: UUID?,
    val nameSurname: String,
    val cpf: String,
    val phone: String,
    @Email
    @Column(unique = true, nullable = false)
    val email: String

) {
    fun toEntity(): Collaborator = Collaborator(
        idCollaborator = this.idCollaborator,
        nameSurname = this.nameSurname,
        cpf = this.cpf,
        cep = "",
        dateOfBirth = null,
        phone = this.phone,
        numberResidence = "",
        rg = "",
        email = this.email,
        schedule = mutableSetOf(),
        addressCollaborator = mutableSetOf()
    )

    companion object {
        fun fromEntity(collaborator: Collaborator) = CollaboratorScheduleResponse(
            idCollaborator = collaborator.idCollaborator,
            nameSurname = collaborator.nameSurname,
            cpf = collaborator.cpf,
            phone = collaborator.phone,
            email = collaborator.email
        )
    }
}
