package com.luciano.microservicocadastrarclient.datamodel

import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.model.AddressClient
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


val formatterCreate = DateTimeFormatter.ofPattern("dd-MM-yyyy")

val addressCreate = AddressClient(
    idAddress = null,
    cep = "17201110",
    road = "Rua Soldado Júlio Pinheiro de Araújo",
    city = "Jaú",
    numberResidence = "51",
    complement = "",
    uf = "SP",
    client = client
)

val returnCreateClient = CreateClientUser(
    nameSurname = "Luciano",
    cep = "12345678",
    cpf = "12345678901",
    dateOfBirth = LocalDate.parse("01-10-1983", formatterCreate),
    registrationDate = LocalDateTime.now(),
    rg = "123456789",
    phone = "999999999",
    numberResidence = "123",
    email = "luciano@gmail.com",
    addressClient = setOf(addressCreate)
)
