package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.address.CepAddress
import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddress
import com.luciano.microservicocadastrarclient.service.service.AddressService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/address")
class AddressController(
    private val addressService: AddressService,
) {
    @PostMapping("/{idClient}")
    fun createAddress(
        @PathVariable("idClient") idClient: Long,
        @RequestBody @Valid cepAddress: CepAddress
    ): ResponseEntity<CepAddress> {
        val address = addressService.createAddress(idClient, cepAddress)
        return ResponseEntity.status(HttpStatus.CREATED).body(CepAddress.fromEntity(address))
    }


    @GetMapping("/alladdress")
    fun getAllAddress(): List<CreateAddress> = addressService.getAllAddress().map {
        CreateAddress.fromEntity(it)
    }

    @GetMapping("/{idAdress}")
    fun getByIdAddress(@PathVariable("/idAddress") idAddress: Long): ResponseEntity<CreateAddress> {
        val address = addressService.getByIdAddress(idAddress)
        return ResponseEntity.status(HttpStatus.OK).body(CreateAddress.fromEntity(address))
    }

}
