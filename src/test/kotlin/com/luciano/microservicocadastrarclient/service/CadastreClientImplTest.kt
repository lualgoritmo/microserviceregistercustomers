package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.datamodel.address
import com.luciano.microservicocadastrarclient.datamodel.addressResponse
import com.luciano.microservicocadastrarclient.datamodel.client
import com.luciano.microservicocadastrarclient.datamodel.returnClient
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.repository.ClientRepository
import com.luciano.microservicocadastrarclient.service.serviceimpl.CadastreClientImpl
import com.luciano.microservicocadastrarclient.service.serviceimpl.ViaCepServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CadastreClientImplTest {

    @InjectMocks
    private lateinit var cadastreClientImpl: CadastreClientImpl

    @Mock
    private lateinit var clientRepository: ClientRepository

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

        verify(clientRepository, times(1)).save(client)
        verify(addressRepository, times(1)).save(address)
        verify(viaCepServiceImpl, times(1)).getAddressByCep(client.cep)

    }
}
