package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.FetchType
import java.util.UUID

@Entity
@Table(name = "tb_addressClient")
@JsonIgnoreProperties("client")
data class AddressGeneric(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idAddress: UUID? = null,
    val cep: String? = null,
    val road: String? = null,
    val city: String?,
    val numberResidence: String? = null,
    val complement: String?,
    val uf: String?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    @JsonBackReference("clientReference")
    @JsonIgnore
    val client: ClientUser? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_collaborator")
    @JsonBackReference("collaboratorReference")
    val collaborator: Collaborator? = null
) {
    override fun hashCode(): Int {
        return idAddress.hashCode()
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddressGeneric

        return idAddress == other.idAddress
    }
}

