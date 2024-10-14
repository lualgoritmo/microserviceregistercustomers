package com.luciano.microservicocadastrarclient.dto

import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser

data class AddressClientResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val numberResidence: String,
    val localidade: String,
    val uf: Char,
    val client: ClientUser
) {
    fun toEntity() = AddressClient(
        cep = this.cep,
        road = this.logradouro,
        city = this.localidade,
        numberResidence = this.numberResidence,
        complement = this.complemento,
        uf = this.uf,
        client = this.client
    )


}
