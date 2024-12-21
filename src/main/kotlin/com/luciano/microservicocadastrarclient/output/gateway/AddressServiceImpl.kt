package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.service.AddressService
import com.luciano.microservicocadastrarclient.service.CadastreClient
import com.luciano.microservicocadastrarclient.service.CollaboratorService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AddressServiceImpl(
    private val clientUserService: CadastreClient,
    private val collaboratorService: CollaboratorService,
    private val addressRepository: AddressRepository,
    private val viaCepServiceImpl: ViaCepServiceImpl
) : AddressService {
    @Transactional
    override fun createAddress(cepAddress: CepAddress, idClient: UUID?, idCollaborator: UUID?): AddressGeneric {
        val clientUser = idClient?.let { clientUserService.getClientById(it) }
        val collaborator = idCollaborator?.let { collaboratorService.findByIdCollaborator(it) }
        val newAddress = viaCepServiceImpl.getAddress(
            cepAddress.cep!!, client = clientUser, collaborator = collaborator,cepAddress.numberResidence ?: ""
        )

        clientUser?.addressClient?.let { address ->
            if(address.any { it.numberResidence == newAddress.numberResidence }) {
                throw IllegalArgumentException("Endereço do Cliente duplicado")
            }
        }

        collaborator?.addressCollaborator?.let { address ->
            if(address.any { it.numberResidence == newAddress.numberResidence }) {
                throw IllegalArgumentException(" Endereço do Collaborador duplicado ")
            }
        }

        val savedAddress = addressRepository.save(newAddress)

        clientUser?.addressClient?.add(savedAddress)

        return savedAddress
    }
    override fun getAllAddress(): List<AddressGeneric> = addressRepository.findAll()
    override fun getByIdAddress(idAddress: UUID): AddressGeneric = addressRepository.findById(idAddress).orElseThrow {
        Exception("Esse cliente não foi encontrado: $idAddress ")

    }

    override fun updateAddressClient(idClient: UUID, idAddress: UUID, updateAddressClient: AddressGeneric): AddressGeneric {
        val existingClient = clientUserService.getClientById(idClient)

        val existingAddress = addressRepository.findById(idAddress).orElseThrow {
            Exception("Este endereço não existe! $idAddress")
        }

        val updateAddress = existingAddress.copy(
            numberResidence = updateAddressClient.numberResidence,
            complement = updateAddressClient.complement,
            road = updateAddressClient.road
        )

        addressRepository.save(updateAddress)

        existingClient.addressClient.removeIf { it.idAddress == idAddress }
        existingClient.addressClient.add(updateAddress)

        return existingAddress
    }

}
