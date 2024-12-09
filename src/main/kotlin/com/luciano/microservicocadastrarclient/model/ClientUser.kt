package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_client_user")
//@EqualsAndHashCode(of = ["idClientUser"])
data class ClientUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idClientUser: UUID? = null,
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

    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference("clientReference")
    var addressClient: MutableSet<AddressGeneric>? = null
) {
    override fun hashCode(): Int {
        return idClientUser.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClientUser

        return idClientUser == other.idClientUser
    }
}
