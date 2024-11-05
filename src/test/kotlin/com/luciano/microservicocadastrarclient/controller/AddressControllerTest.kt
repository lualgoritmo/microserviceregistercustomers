package com.luciano.microservicocadastrarclient.controller

import com.luciano.microservicocadastrarclient.datamodel.address
import com.luciano.microservicocadastrarclient.datamodel.client
import com.luciano.microservicocadastrarclient.datamodel.returnClientCreate
import com.luciano.microservicocadastrarclient.input.controller.AddressController
import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.input.dto.address.UpdateAddressClient
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.output.gateway.AddressServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID

@ExtendWith(SpringExtension::class)
@WebMvcTest(AddressController::class)
class AddressControllerTest {

    @MockBean
    private lateinit var addressServiceImpl: AddressServiceImpl

    private lateinit var addressController: AddressController
    @BeforeEach
    fun setUp() {
        addressController = AddressController(addressServiceImpl)
    }
    @Test
    fun `when POST createAddress is called, it should create a new address and return 201 status`() {

        val idClient = UUID.randomUUID()
        val cepAddress = CepAddress(cep = "17201110", numberResidence = "51", client = client)

        val createdAddress = AddressClient(
            idAddress = UUID.randomUUID(),
            cep = cepAddress.cep,
            road = "Rua Teste",
            city = "Teste City",
            numberResidence = cepAddress.numberResidence,
            complement = "",
            uf = "SP",
            client = client
        )

        whenever(addressServiceImpl.createAddress(eq(idClient), any())).thenReturn(createdAddress)

        val response = addressController.createAddress(idClient, cepAddress)

        val addressResponse = response.body

        assertNotNull(addressResponse)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(cepAddress.cep, addressResponse?.cep)
        assertEquals(cepAddress.numberResidence, addressResponse?.numberResidence)

        verify(addressServiceImpl, times(1)).createAddress(eq(idClient), any())
    }
    @Test
    fun `when GET getAllListAddress is called, it should return list address`() {
        whenever(addressServiceImpl.getAllAddress()).thenReturn(listOf(address, address))

        val response = addressController.getAllAddress()
        val addressResponse = response.body

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(2, response.body?.size)
        assertEquals("17201110", addressResponse?.get(0)?.cep)

        verify(addressServiceImpl, times(1)).getAllAddress()
    }

    @Test
    fun `when GET getClientById is called, it should return one address`() {

        whenever(addressServiceImpl.getByIdAddress(any())).thenReturn(address)

        val response  = addressController.getByIdAddress(returnClientCreate().idClientUser!!)

        val address = response.body

        assertNotNull(address)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body!!.cep, address?.cep)
    }

    @Test
    fun `when updateAddressClient is called with valid data, it should update the address successfully`() {
        val idClient = UUID.randomUUID()
        val idAddress = UUID.randomUUID()

        val existingAddress = AddressClient(
            idAddress = idAddress,
            cep = "17201110",
            road = "Rua Antiga",
            city = "Cidade Antiga",
            numberResidence = "51",
            complement = "Apto 1",
            uf = "SP",
            client = client
        )

        val updatedAddressClient = AddressClient(
            idAddress = idAddress,
            cep = "17201111",
            road = "Rua Nova",
            city = "Cidade Nova",
            numberResidence = "52",
            complement = "Apto 2",
            uf = "SP",
            client = client
        )

        val updateAddressClientDto = UpdateAddressClient.fromEntity(updatedAddressClient)

        whenever(addressServiceImpl.getByIdAddress(idAddress)).thenReturn(existingAddress)
        whenever(addressServiceImpl.updateAddressClient(idClient, idAddress, existingAddress))
            .thenReturn(updatedAddressClient)

        val result = updateAddressClientDto?.let { addressController.updateAddressClient(idClient, idAddress, it) }

        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result?.statusCode)
        assertEquals(updatedAddressClient.road, result?.body?.road)
        assertEquals(updatedAddressClient.numberResidence, result?.body?.numberResidence)
        assertEquals(updatedAddressClient.complement, result?.body?.complement)

        verify(addressServiceImpl, times(1)).getByIdAddress(idAddress)
        verify(addressServiceImpl, times(1)).updateAddressClient(idClient, idAddress, existingAddress)
    }

}
