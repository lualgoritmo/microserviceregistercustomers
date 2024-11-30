package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.ServiceToDo
import com.luciano.microservicocadastrarclient.repository.ServiceToDoRepository
import com.luciano.microservicocadastrarclient.service.service.TODOService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ServiceToDoImpl(private val serviceToDoRepository: ServiceToDoRepository): TODOService {
    override fun createToDoService(serviceToDo: ServiceToDo) = serviceToDoRepository.save(serviceToDo)

    override fun getServiceToDoWithId(idServiceToDo: UUID): TODOService {
        TODO("Not yet implemented")
    }
}