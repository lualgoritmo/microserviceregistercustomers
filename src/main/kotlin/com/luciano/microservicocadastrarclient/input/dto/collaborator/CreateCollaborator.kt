package com.luciano.microservicocadastrarclient.input.dto.collaborator

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.Collaborator
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.UUID

data class CreateCollaborator(
    val idCollaborator: UUID? = null,
    @NotBlank(message = "Não deixe o campo nome vazio")
    val name: String,
    @NotBlank(message = "Não deixe o campo data de nascimento vazio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM,yyyy")
    val dateBirth: LocalDateTime,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY HH:mm:ss")
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    @NotBlank(message = "Não deixe o campos gênero vazio")
    val gender: String,
    @NotBlank(message = "Não deixe o endereço vazio")
    val addressCollaborator: Set<AddressGeneric> = emptySet()
) {
    fun toEntity() = Collaborator(
        idCollaborator = this.idCollaborator,
        name = this.name,
        dateBirth = this.dateBirth,
        registrationDate = this.registrationDate,
        gender = this.gender,
        addressCollaborator = mutableSetOf()
    )
    companion object {
        fun fromEntity(collaborator: Collaborator) = CreateCollaborator(
            idCollaborator = collaborator.idCollaborator,
            name = collaborator.name,
            dateBirth = collaborator.dateBirth,
            registrationDate = collaborator.registrationDate,
            gender = collaborator.gender,
            addressCollaborator = collaborator.addressCollaborator
        )
    }
}
