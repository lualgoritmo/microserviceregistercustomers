package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.input.controller.dto.AddressClientResponse

interface ViaCepService {

    fun getAddressByCep(cep:String): AddressClientResponse

}
