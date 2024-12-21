package com.luciano.microservicocadastrarclient.service.dtoservice

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser

data class AddressGenericResponse(
    val cep: String? = null,
    val logradouro: String? = null,
    val complemento: String? = null,
    val bairro: String? = null,
    val localidade: String? = null,
    val uf: String? = null,
    val numberResidence: String? = null,
    val ibge: String? = null,
    val gia: String? = null,
    val ddd: String? = null,
    val siafi: String? = null
) {
    fun toEntity(client: ClientUser) = AddressGeneric(
        cep = this.cep,
        road = this.logradouro ?: "",
        city = this.localidade ?: "",
        numberResidence = client.numberResidence ?: "",
        complement = this.complemento ?: "",
        uf = this.uf ?: "",
        client = client
    )
}
