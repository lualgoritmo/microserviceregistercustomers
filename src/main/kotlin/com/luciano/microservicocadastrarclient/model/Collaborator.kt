package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import java.time.LocalDateTime
import java.util.UUID
@Entity
@Table(name = "tb_collaborator")
data class Collaborator(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idCollaborator: UUID ? = null,
    val name: String,
    val dateBirth: LocalDateTime,
    val gender: String,
    @OneToMany(mappedBy = "collaborator", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val addressCollaborator: List<AddressClient>
)
