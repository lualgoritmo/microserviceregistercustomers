package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.AddressClient
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.service.service.AddressService
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val clientUserService: CadastreClient,
    private val addressRepository: AddressRepository,
    private val viaCepServiceImpl: ViaCepServiceImpl
) : AddressService {

    override fun createAdress(idClient: Long, idAddress: Long): AddressClient {
        val clientUser = clientUserService.getClientById(idClient)
            ?: throw IllegalArgumentException("Cliente com ID $idClient não encontrado.")

        val existingAddress = addressRepository.findById(idAddress).orElse(null)

        val newAddress = viaCepServiceImpl.getAddressClient(clientUser)

        if (existingAddress == null) {
            clientUser.addressClient.add(newAddress)
            addressRepository.save(newAddress)
        } else {
            throw IllegalArgumentException("Endereço com ID $idAddress já existe.")
        }

        return newAddress

    }
    override fun getAllAddress(): List<AddressClient> = addressRepository.findAll()
    override fun getByIdAddress(idAddress: Long): AddressClient = addressRepository.findById(idAddress).orElseThrow {
        Exception("ClientUser not found with id: $idAddress ")

    }

}
