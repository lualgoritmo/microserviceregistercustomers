package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.datamodel.client
import com.luciano.microservicocadastrarclient.datamodel.address
import com.luciano.microservicocadastrarclient.datamodel.addressResponse
import com.luciano.microservicocadastrarclient.datamodel.updateClient
import com.luciano.microservicocadastrarclient.datamodel.returnClient
import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.repository.ClientUserRepository
import com.luciano.microservicocadastrarclient.output.gateway.CadastreClientImpl
import com.luciano.microservicocadastrarclient.output.gateway.ViaCepServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CadastreClientImplTest {

    @InjectMocks
    private lateinit var cadastreClientImpl: CadastreClientImpl

    @Mock
    private lateinit var clientRepository: ClientUserRepository

    @Mock
    private lateinit var viaCepServiceImpl: ViaCepServiceImpl

    @Mock
    private lateinit var addressRepository: AddressRepository
    @Test
    fun `when cadastreclient was called, it should save the client`() {

        whenever(clientRepository.save(client)).thenReturn(returnClient())
        whenever(addressRepository.save(address)).thenReturn(address)
        whenever(viaCepServiceImpl.getAddressByCep(client.cep)).thenReturn(addressResponse)

        val response = cadastreClientImpl.cadastreClient(returnClient())

        assertEquals(client.cep, response.cep)
        assertEquals(client.rg, response.rg)
        assertEquals(client.cpf, response.cpf)

        verify(clientRepository, times(1)).save(client)
        verify(addressRepository, times(1)).save(address)
        verify(viaCepServiceImpl, times(1)).getAddressByCep(client.cep)

    }
    @Test
    fun `when getClientById was called, it shoud return the client`() {

        whenever(clientRepository.findById(client.idClientUser!!)).thenReturn(Optional.of(client))

        val existingClientUser = cadastreClientImpl.getClientById(1)

        assertEquals(client.idClientUser, existingClientUser.idClientUser)
        assertEquals(client.rg, existingClientUser.rg)
        assertEquals("12345678901", existingClientUser.cpf)

        verify(clientRepository, times(1)).findById(client.idClientUser!!)
    }
    @Test
    fun `When getClientById fails, it should return an exception`() {

        whenever(clientRepository.findById(any())).thenReturn(Optional.empty())

        val exception = assertThrows<Exception> { cadastreClientImpl.getClientById(13) }

        assertEquals("ClientUser not found with id: 13 ", exception.message)

        verify(clientRepository, times(1)).findById(13)

    }
    @Test
    fun `when updateClientUsers is run it should return an updated client`() {

        whenever(clientRepository.findById(client.idClientUser!!)).thenReturn(Optional.of(client))

        val existingClient = client.copy(
            cep = "12345678",
            phone = "11111111111",
            numberResidence = "333",
            email = "update@update.com"
        )
        val updateClient = UpdateClient.fromEntity(existingClient)

        whenever(clientRepository.save(updateClient.toEntity(existingClient))).thenReturn(existingClient)

        val expectedUpdateClient = cadastreClientImpl.updateClientUser(1, updateClient)

        assertEquals(expectedUpdateClient.cep, updateClient.cep)

        verify(clientRepository, times(1)).findById(client.idClientUser!!)
        verify(clientRepository, times(1)).save(updateClient.toEntity(existingClient))

    }
    @Test
    fun `when updateClientUsers fails shoud an return exception`() {

        whenever(clientRepository.findById(any())).thenReturn(Optional.empty())

        val exception = assertThrows<Exception> {
            cadastreClientImpl
                .updateClientUser(client.idClientUser!!, client = updateClient)
        }

        assertEquals("ClientUser not found with id: ${client.idClientUser} ", exception.message)

        verify(clientRepository, times(1)).findById(any())
    }

}
