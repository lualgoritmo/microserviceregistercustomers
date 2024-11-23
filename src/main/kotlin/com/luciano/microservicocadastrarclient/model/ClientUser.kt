package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_client_user")
data class ClientUser(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val idClientUser: UUID? = null,
    val nameSurname: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val dateOfBirth: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val cpf: String,
    val cep: String,
    val numberResidence: String?=null,
    val phone: String,
    val rg: String,
    @Email
    val email: String,
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val addressClient: MutableSet<AddressGeneric> = mutableSetOf(),

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collaborator")
    @JsonBackReference
    val collaborator: List<Collaborator> = listOf()
)
