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
            cepAddress.cep!!, clientUser, cepAddress.numberResidence?:"")

        if (clientUser.addressClient.any { it.cep == newAddress.cep }) {
            throw IllegalArgumentException("Endereço duplicado.")
        }

        val savedAddress = addressRepository.save(newAddress)

        clientUser.addressClient.add(savedAddress)

        return savedAddress
    }
    override fun getAllAddress(): List<AddressClient> = addressRepository.findAll()
    override fun getByIdAddress(idAddress: Long): AddressClient = addressRepository.findById(idAddress).orElseThrow {
        Exception("ClientUser not found with id: $idAddress ")

    }

}
