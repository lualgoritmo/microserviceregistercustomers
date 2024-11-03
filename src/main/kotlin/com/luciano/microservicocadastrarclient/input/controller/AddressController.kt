package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddress
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
    @PostMapping("/{idClient}")
    fun createAddress(
        @PathVariable("idClient") idClient: UUID,
        @RequestBody @Valid cepAddress: CepAddress
    ): ResponseEntity<CreateAddress> {
        val address = addressService.createAddress(idClient, cepAddress)
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAddress.fromEntity(address))
    }

    @GetMapping("/alladdress")
    fun getAllAddress(): ResponseEntity<List<CreateAddress>> {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAddress().map {
            CreateAddress.fromEntity(it)
        })
    }

    @GetMapping("/{idAdress}")
    fun getByIdAddress(@PathVariable idAddress: UUID): ResponseEntity<CreateAddress> {
        val address = addressService.getByIdAddress(idAddress)
        return ResponseEntity.status(HttpStatus.OK).body(CreateAddress.fromEntity(address))
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
            idClient, idAddress, updateAddress
        )

        return ResponseEntity.status(HttpStatus.OK).body(UpdateAddressClient.fromEntity(savedAddress))
    }

}
