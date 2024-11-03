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
import java.util.UUID

@Entity
@Table(name = "tb_addressClient")
@JsonIgnoreProperties("client")
data class AddressClient(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idAddress: UUID? = null,
    val cep: String?=null,
    val road: String? = null,
    val city: String?,
    val numberResidence: String?= null,
    val complement: String?,
    val uf: String?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    @JsonBackReference
    val client: ClientUser? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AddressClient) return false
        return idAddress == other.idAddress
    }

    override fun hashCode(): Int {
        return idAddress?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "AddressClient(idAddress=$idAddress, cep='$cep', road=$road, city='$city', numberResidence=$numberResidence, complement='$complement', uf='$uf')"
    }
}