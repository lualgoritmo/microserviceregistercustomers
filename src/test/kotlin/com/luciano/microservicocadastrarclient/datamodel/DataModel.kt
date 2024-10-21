package com.luciano.microservicocadastrarclient.datamodel

import com.luciano.microservicocadastrarclient.dto.AddressClientResponse
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun returnClient(): ClientUser {
    client.addressClient.add(address)
    return client
}

val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

val client = ClientUser(
    nameSurname = "Terceiro Teste",
    cpf = "12345678901",
    cep = "17201110",
    dateOfBirth = LocalDate.parse("01-10-1983", dateFormatter),
    registrationDate = LocalDateTime.now(),
    numberResidence = "51",
    phone = "1234567890",
    rg = "1234567",
    email = "johndoe@example.com",
    addressClient = mutableSetOf() // Inicializa o conjunto de endereços
)

val address = AddressClient(
    idAddress = null,
    cep = "17201110",
    road = "Rua Soldado Júlio Pinheiro de Araújo",
    city = "Jaú",
    numberResidence = "51",
    complement = "",
    uf = "SP",
    client = client // Referência ao cliente
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