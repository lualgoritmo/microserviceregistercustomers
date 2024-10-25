package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddress
import com.luciano.microservicocadastrarclient.service.service.AddressService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/v1/address")
class AddressController(
    private val addressService: AddressService,
    private val clientService: AddressService
) {
    @PostMapping("/createAddress")
    fun createAddress(
        @PathVariable("/{idClient}") idClient: Long,
        @PathVariable("{idAdress}") idAddress: Long,
        @RequestBody @Valid createAddress: CreateAddress
    ): ResponseEntity<CreateAddress> {

        val address = addressService.createAdress(idClient, idAddress)
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateAddress.fromEntity(address))

    }
    @GetMapping("/alladdress")
    fun getAllAddress(): List<CreateAddress> = addressService.getAllAddress().map {
        CreateAddress.fromEntity(it)
    }
    @GetMapping("/getaddress-id")
    fun getByIdAddress(@PathVariable("/{idAddress}") idAddress: Long): ResponseEntity<CreateAddress> {
        val address = addressService.getByIdAddress(idAddress)
        return ResponseEntity.status(HttpStatus.OK).body(CreateAddress.fromEntity(address))
    }

}
