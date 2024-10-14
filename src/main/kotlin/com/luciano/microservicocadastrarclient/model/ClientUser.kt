package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime
@Entity
@Table(name = "tb_client")
data class ClientUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idClient: Long? = null,
    val nameSurname: String,
    val cpf: String,
    val cep: String,
    val dateOfBirth: LocalDate,
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val phone:String,
    val rg: String,
    @Email
    val email: String,
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
    @JsonBackReference
    var addressClient: Set<AddressClient>
)
