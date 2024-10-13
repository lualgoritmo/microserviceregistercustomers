package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tb_client")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idClient: UUID? = null,
    val nameSurname: String,
    @CPF
    val cpf: CPF,
    val cep: String,
    val dateOfBirth: LocalDate,
    val registrationDate: LocalDateTime = LocalDateTime.now(),
    val phone:String,
    val rg: String,
    @Email
    val email: String,
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
    @JsonBackReference
    val addressClient: List<AddressClient> = emptyList()
)
