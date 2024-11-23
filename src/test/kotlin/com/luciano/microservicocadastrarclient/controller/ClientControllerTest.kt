package com.luciano.microservicocadastrarclient.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.microservicocadastrarclient.datamodel.client
import com.luciano.microservicocadastrarclient.datamodel.dateFormatter
import com.luciano.microservicocadastrarclient.datamodel.returnClient
import com.luciano.microservicocadastrarclient.datamodel.returnClientCreate
import com.luciano.microservicocadastrarclient.input.controller.ClientController
import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.output.gateway.CadastreClientImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@ExtendWith(SpringExtension::class)
@WebMvcTest(ClientController::class)
class ClientControllerTest {

    @MockBean
    private lateinit var clientUserService: CadastreClientImpl

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMVC: MockMvc

    private lateinit var clientController: ClientController

    @BeforeEach
    fun setUp() {
        clientController = ClientController(clientUserService)
    }
    @Test
    fun `when POST cadastreClient is called, it should return created client`() {
        val clientUserEntity = ClientUser(
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

        val addressClient = AddressGeneric(
            idAddress = null,
            cep = "17201110",
            road = "Rua Soldado Júlio Pinheiro de Araújo",
            city = "Jaú",
            numberResidence = "51",
            complement = "",
            uf = "SP",
            client = clientUserEntity,
            null
        )

        clientUserEntity.addressClient.add(addressClient)

        val createClientUser = CreateClientUser(
            nameSurname = "Terceiro Teste",
            cpf = "12345678901",
            cep = "17201110",
            dateOfBirth = LocalDate.parse("01-10-1983", dateFormatter),
            numberResidence = "51",
            phone = "1234567890",
            rg = "1234567",
            email = "johndoe@example.com",
            addressClient = setOf(addressClient)
        )

        whenever(clientUserService.cadastreClient(any())).thenReturn(clientUserEntity)

        mockMVC.perform(
            MockMvcRequestBuilders.post("/v1/clients/create-client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createClientUser))
        )
            .andExpect(status().isCreated)
            .andExpect(content().json(objectMapper.writeValueAsString(CreateClientUser.fromEntity(clientUserEntity))))
    }
    @Test
    fun `when GET getAllListClients is called, it should return list clients`() {

        whenever(clientUserService.getAllListClients()).thenReturn(listOf(returnClient(), returnClient()))

        val response = mockMVC.perform(
            MockMvcRequestBuilders.get("/v1/clients/allclients")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andReturn()

        val responseBody = response.response.contentAsString
        val clientList: List<ClientUser> = objectMapper.readValue(responseBody,
            object : TypeReference<List<ClientUser>>() {})

        assertNotNull(clientList)
        assertEquals(2, clientList.size)
        assertEquals("Terceiro Teste", clientList[1].nameSurname)
    }
    @Test
    fun `when GET getClientById is called, it should return one client`() {

        whenever(clientUserService.getClientById(any())).thenReturn(returnClientCreate())

        val response = clientController.getByIdClient(returnClientCreate().idClientUser!!)

        val client = response.body

        assertNotNull(client)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Terceiro Teste", client?.nameSurname)
    }
    @Test
    fun `when updateClientUser is called it shoud return a client updated`() {
        val existingClient = client

        val updateClient = existingClient.copy(
            idClientUser = existingClient.idClientUser,
            cep = "123456789",
            numberResidence = "123",
            email = "novoemail@teste.com"
        )

        whenever(clientUserService.updateClientUser(idClient = any(), client = any())).thenReturn(client)

        val response = clientController.updateClient(
            idClient = existingClient.idClientUser!!,
            client = UpdateClient.fromEntity(updateClient)
        )

        assertNotNull(response.body)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotEquals(updateClient.cep, response.body?.cep)
    }
}
