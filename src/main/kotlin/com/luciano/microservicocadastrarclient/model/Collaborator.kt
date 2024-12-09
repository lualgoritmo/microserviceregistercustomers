package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import lombok.EqualsAndHashCode
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_collaborator")
@EqualsAndHashCode(of = ["idCollaborator"])
data class Collaborator(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idCollaborator: UUID? = null,
    val name: String,
    val dateBirth: LocalDateTime,
    val gender: String,
    val registrationDate: LocalDateTime,
    @OneToMany(mappedBy = "collaborator", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference("collaboratorReference")
    val addressCollaborator: MutableSet<AddressGeneric>
)


