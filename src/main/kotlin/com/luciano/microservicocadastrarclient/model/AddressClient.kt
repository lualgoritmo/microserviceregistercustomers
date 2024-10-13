package com.luciano.microservicocadastrarclient.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tb_addressClient")
data class AddressClient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAddress: UUID ?= null,
    val cep: String,
    val road: String,
    val city: String,
    val numberResidence: String,
    val complement: String,
    val uf: Char,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    val client: Client
)
