package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.input.dto.client.UpdateClient
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.repository.ClientUserRepository
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import jakarta.transaction.Transactional
import org.hibernate.Hibernate
import org.springframework.stereotype.Service
import java.util.*


@Service
class CadastreClientImpl(
    private val clientRepository: ClientUserRepository,
    private val viaCep: ViaCepServiceImpl,
    private val addressRepository: AddressRepository
) : CadastreClient {

    @Transactional
    override fun cadastreClient(client: ClientUser): ClientUser {
        // Salva o cliente e verifica se o retorno é nulo
        val savedClient = clientRepository.save(client)
        requireNotNull(savedClient) { "Failed to save the client" }

        // Inicializa addressClient se for nulo
        if (savedClient.addressClient == null) {
            savedClient.addressClient = mutableSetOf()
        }

        // Recupera o endereço usando o serviço ViaCep
        val address = viaCep.getAddressClient(
            cep = client.cep,
            client = savedClient,
            numberResidence = client.numberResidence ?: ""
        ) ?: throw IllegalArgumentException("Failed to retrieve address for CEP: ${client.cep}")

        // Adiciona o endereço ao cliente
        savedClient.addressClient.add(address)

        // Salva o endereço no repositório
        addressRepository.save(address)

        return savedClient
    }


    @Transactional
    override fun getClientById(idClient: UUID): ClientUser = clientRepository.findById(idClient).orElseThrow {
        IllegalArgumentException("Cliente com ID $idClient não encontrado.")
    }.apply {
        Hibernate.initialize(this.addressClient)
    }


    override fun getAllListClients(): List<ClientUser> = clientRepository.findAll()

    @Transactional
    override fun updateClientUser(idClient: UUID, client: UpdateClient): ClientUser {
        val existingClient = clientRepository.findById(idClient)
            .orElseThrow {
                Exception("ClientUser not found with id: $idClient ")
            }
        val updatedClient = client.toEntity(existingClient)

        return clientRepository.save(updatedClient)
    }

    @Transactional
    override fun deleteClientId(idClient: UUID) {
        val existingClient = clientRepository.findById(idClient)
            .orElseThrow {
                NoSuchElementException("ClientUser not found with id: $idClient ")
            }
        clientRepository.delete(existingClient)
    }

}

