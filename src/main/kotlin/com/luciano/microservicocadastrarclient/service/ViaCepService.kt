package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.service.dtoservice.AddressGenericResponse

interface ViaCepService {
    fun getAddressByCep(cep:String): AddressGenericResponse

}
