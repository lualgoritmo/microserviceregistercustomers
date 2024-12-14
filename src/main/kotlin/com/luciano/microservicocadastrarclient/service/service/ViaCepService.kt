package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.service.dto.AddressGenericResponse

interface ViaCepService {
    fun getAddressByCep(cep:String): AddressGenericResponse

}
