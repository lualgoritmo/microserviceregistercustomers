package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.service.service.AddressService
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
@Service
class AddressServiceImpl(
    private val clientUserService: CadastreClient,
    private val addressRepository: AddressRepository,
    private val viaCepServiceImpl: ViaCepServiceImpl
) : AddressService {
    @Transactional
    override fun createAddress(idClient: Long, cepAddress: CepAddress): AddressClient {

        val clientUser = clientUserService.getClientById(idClient)

        val newAddress = viaCepServiceImpl.getAddressClient(
            cepAddress.cep!!, clientUser, cepAddress.numberResidence ?: ""
        )

        if (clientUser.addressClient.any { it.numberResidence == newAddress.numberResidence }) {
            throw IllegalArgumentException("Endereço duplicado.")
        }

        val savedAddress = addressRepository.save(newAddress)

        clientUser.addressClient.add(savedAddress)

        return savedAddress
    }

    override fun getAllAddress(): List<AddressClient> = addressRepository.findAll()
    override fun getByIdAddress(idAddress: Long): AddressClient = addressRepository.findById(idAddress).orElseThrow {
        Exception("Esse cliente não foi encontrado: $idAddress ")

    }

    override fun updateAddressClient(idClient: Long, idAddress: Long, updateAddressClient: AddressClient): AddressClient {
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
