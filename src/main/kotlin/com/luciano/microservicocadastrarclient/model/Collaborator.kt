package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import lombok.EqualsAndHashCode
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_collaborator")
@EqualsAndHashCode(of = ["idCollaborator"])
data class Collaborator(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idCollaborator: UUID? = null,
    val nameSurname: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val dateOfBirth: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val cpf: String,
    val cep: String,
    val numberResidence: String? = null,
    val phone: String,
    val rg: String,
    @Email
    val email: String,
    @OneToMany(mappedBy = "collaborator", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference("collaboratorReference")
    var addressCollaborator: MutableSet<AddressGeneric>
)


