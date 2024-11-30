package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.model.ServiceToDo
import java.util.UUID

interface TODOService {
    fun createToDoService(serviceToDo: ServiceToDo): ServiceToDo
    fun getServiceToDoWithId(idServiceToDo: UUID): TODOService
}