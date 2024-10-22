package com.luciano.microservicocadastrarclient.service.dto

import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser

data class AddressClientResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val numberResidence: String?=null,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
) {
    fun toEntity(client: ClientUser) = AddressClient(
        cep = this.cep,
        road = this.logradouro ?: "",
        city = this.localidade ?: "",
        numberResidence = client.numberResidence?: "",
        complement = this.complemento ?: "",
        uf = this.uf ?: "",
        client = client
    )
}

