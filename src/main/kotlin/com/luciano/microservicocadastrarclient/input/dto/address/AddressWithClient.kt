package com.luciano.microservicocadastrarclient.input.dto.address

import java.util.*

data class ClientWithAddressesDTO(
    val idClientUser: UUID,
    val nameSurname: String,
    val dateOfBirth: String,
    val registrationDate: String,
    val cpf: String,
    val numberResidence: String,
    val phone: String,
    val rg: String,
    val email: String,
    val addressClient: List<CreateAddressClient>
)
