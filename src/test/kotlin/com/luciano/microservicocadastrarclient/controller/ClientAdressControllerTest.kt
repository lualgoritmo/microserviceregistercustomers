package com.luciano.microservicocadastrarclient.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.microservicocadastrarclient.datamodel.returnClient
import com.luciano.microservicocadastrarclient.datamodel.returnCreateClient
import com.luciano.microservicocadastrarclient.input.controller.ClientAdressController
import com.luciano.microservicocadastrarclient.service.serviceimpl.CadastreClientImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(ClientAdressController::class)
class ClientAdressControllerTest {

    @MockBean
    private lateinit var clientUserService: CadastreClientImpl

    @Autowired
    private lateinit var objectMappeer: ObjectMapper

    @Autowired
    private lateinit var mockMVC: MockMvc

    @Test
    fun `when POST cadastreClient is called, it should return created client`() {

        whenever(clientUserService.cadastreClient(returnClient())).thenReturn(returnClient())

        mockMVC.perform(MockMvcRequestBuilders.post("/v1/clients/createclient")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMappeer.writeValueAsString(returnCreateClient)))
            .andExpect(status().isCreated)
    }

}
