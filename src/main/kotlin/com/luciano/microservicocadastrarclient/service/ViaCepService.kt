package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.dto.AddressClientResponse

interface ViaCepService {

    fun getAddressByCep(cep:String): AddressClientResponse

}
