package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.repository.ClientUserRepository
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import com.luciano.microservicocadastrarclient.service.service.ViaCepService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CadastreClientImpl(
    private val clientRepository: ClientUserRepository,
    private val viaCep: ViaCepService,
    private val addressRepository: AddressRepository
) : CadastreClient {

    @Transactional
    override fun cadastreClient(client: ClientUser): ClientUser {

        val savedClient = clientRepository.save(client)

        if (savedClient.addressClient == null) {
            savedClient.addressClient = mutableSetOf()
        }
        val address = getAddressClient(savedClient)

        savedClient.addressClient.add(address)

        addressRepository.save(address)
        return savedClient
    }

    @Transactional
    override fun getClientById(idClient: Long): ClientUser {
        return clientRepository.findById(idClient).orElseThrow {
            Exception("ClientUser not found with id: $idClient ")
        }
    }

    override fun getAllListClients(): List<ClientUser> = clientRepository.findAll()

    @Transactional
    override fun updateClientUser(idClient: Long, client: UpdateClient): ClientUser {
        val existingClient = clientRepository.findById(idClient)
            .orElseThrow {
                Exception("ClientUser not found with id: $idClient ")
            }
        val updatedClient = client.toEntity(existingClient)

        return clientRepository.save(updatedClient)
    }

    @Transactional
    override fun deleteClientId(idClient: Long) {
        val existingClient = clientRepository.findById(idClient)
            .orElseThrow {
                NoSuchElementException("ClientUser not found with id: $idClient ")
            }
        clientRepository.delete(existingClient)
    }

    public fun getAddressClient(client: ClientUser): AddressClient {

        val addressResponse = viaCep.getAddressByCep(client.cep)

        return AddressClient(
            cep = addressResponse.cep,
            road = addressResponse.logradouro ?: "Logradouro não informado",
            city = addressResponse.localidade,
            numberResidence = client.numberResidence ?: "",
            complement = addressResponse.complemento ?: "",
            uf = addressResponse.uf ?: "UF não informada",
            client = client
        )
    }

}
