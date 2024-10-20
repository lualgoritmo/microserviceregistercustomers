package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.FetchType

@Entity
@Table(name = "tb_addressClient")
@JsonIgnoreProperties("client")
data class AddressClient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAddress: Long? = null,
    val cep: String,
    val road: String? = null,
    val city: String,
    val numberResidence: String?,
    val complement: String,
    val uf: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    @JsonBackReference
    val client: ClientUser
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AddressClient) return false
        return idAddress == other.idAddress
    }

    override fun hashCode(): Int {
        return idAddress?.hashCode() ?: 0
    }
}
