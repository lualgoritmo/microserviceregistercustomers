package com.luciano.microservicocadastrarclient.datamodel

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.service.dto.AddressClientResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun returnClientCreate(): ClientUser {
    client.addressClient.add(address)
    return client
}

fun returnClient(): ClientUser {
    val newClient = client.copy(addressClient = mutableSetOf(address.copy()))
    return newClient
}


val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

val client = ClientUser(
    idClientUser = UUID.randomUUID(),
    nameSurname = "Terceiro Teste",
    cpf = "12345678901",
    cep = "17201110",
    dateOfBirth = LocalDate.parse("01-10-1983", dateFormatter),
    registrationDate = LocalDateTime.now(),
    numberResidence = "51",
    phone = "1234567890",
    rg = "1234567",
    email = "johndoe@example.com",
    addressClient = mutableSetOf()
)

val address = AddressGeneric(
    idAddress = null,
    cep = "17201110",
    road = "Rua Soldado Júlio Pinheiro de Araújo",
    city = "Jaú",
    numberResidence = "51",
    complement = "",
    uf = "SP",
    client = client
)

val addressResponse = AddressClientResponse(
    cep = "17201110",
    logradouro = "Rua Soldado Júlio Pinheiro de Araújo",
    localidade = "Jaú",
    complemento = "",
    bairro = "Crispin",
    uf = "SP",
    numberResidence = "13",
    ibge = "12354",
    gia = "12",
    ddd = "27",
    siafi = "00"
)

val updateClient = UpdateClient(
    cep = "00000000",
    phone = "149123456789",
    numberResidence = "130",
    email = "teste@teste.com"
)

val cepAddress = CepAddress(
     idAddress = UUID.randomUUID(),
     cep = "17208531",
     numberResidence = "51",
     client = client
)