package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.service.dto.AddressClientResponse

interface ViaCepService {

    fun getAddressByCep(cep:String): AddressClientResponse

}
