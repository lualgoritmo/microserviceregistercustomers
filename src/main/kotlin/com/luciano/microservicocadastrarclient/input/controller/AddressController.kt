package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddressClient
import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddressCollaborator
import com.luciano.microservicocadastrarclient.input.dto.address.UpdateAddressClient
import com.luciano.microservicocadastrarclient.service.service.AddressService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@RestController
@RequestMapping("/v1/address")
class AddressController(
    private val addressService: AddressService,
) {
    @PostMapping("/create-address-client/{idClient}")
    fun createAddressClient(
        @PathVariable("idClient") idClient: UUID,
        @RequestBody @Valid cepAddress: CepAddress
    ): ResponseEntity<CreateAddressClient> {
        val address = addressService.createAddress(cepAddress = cepAddress, idClient = idClient, )
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAddressClient.fromEntity(addressClient = address))
    }
    @PostMapping("/create-address-collaborator/{idCollaborator}")
    fun createAddressCollaborator(
        @PathVariable("idCollaborator") idCollaborator:UUID,
        @RequestBody @Valid cepAddress: CepAddress
    ): ResponseEntity<CreateAddressCollaborator> {
        val address = addressService.createAddress(cepAddress = cepAddress, idCollaborator = idCollaborator)
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAddressCollaborator.fromEntity(addressClient = address))
    }
    @GetMapping("/alladdress")
    fun getAllAddress(): ResponseEntity<List<CreateAddressClient>> {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAddress().map {
            CreateAddressClient.fromEntity(addressClient = it)
        })
    }

    @GetMapping("/{idAdress}")
    fun getByIdAddress(@PathVariable idAddress: UUID): ResponseEntity<CreateAddressClient> {
        val address = addressService.getByIdAddress(idAddress)
        return ResponseEntity.status(HttpStatus.OK).body(CreateAddressClient.fromEntity(addressClient = address))
    }
    @PutMapping("/update-address/{idClient}/{idAddress}")
    fun updateAddressClient(
        @PathVariable idClient: UUID,
        @PathVariable idAddress: UUID,
        @RequestBody @Valid updateAddress: UpdateAddressClient
    ): ResponseEntity<UpdateAddressClient> {
        val existingAddress = addressService.getByIdAddress(idAddress)

        val updateAddress = updateAddress.toEntity(existingAddress = existingAddress)

        val savedAddress = addressService.updateAddressClient(
            idClient = idClient, idAddress = idAddress, updateAddressClient = updateAddress
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(UpdateAddressClient.fromEntity(savedAddress))
    }

}
