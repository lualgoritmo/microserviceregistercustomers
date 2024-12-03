package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.ServiceSchedule
import com.luciano.microservicocadastrarclient.repository.ServiceToDoRepository
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import com.luciano.microservicocadastrarclient.service.service.CollaboratorService
import com.luciano.microservicocadastrarclient.service.service.ScheduleService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ScheduleServiceImpl(
    private val serviceToDoRepository: ServiceToDoRepository,
    private val clientService: CadastreClient,
    private val collaboratorService: CollaboratorService
): ScheduleService {
    @Transactional
    override fun createScheduleService(serviceSchedule: ServiceSchedule): ServiceSchedule {
        val client = serviceSchedule.client.idClientUser?.let { clientService.getClientById(it) }
        val collaborator = serviceSchedule.collaborator.mapNotNull {
            collaborator -> collaborator.idCollaborator
        }
        val listCollaborators = collaboratorService.findAllById(collaborator)

        val service = ServiceSchedule(
            description = serviceSchedule.description,
            price = serviceSchedule.price,
            serviceDate = serviceSchedule.serviceDate,
            serviceHours = serviceSchedule.serviceHours,
            collaborator = listCollaborators,
            client = client!!
        )
        return serviceToDoRepository.save(service)
    }

    override fun getServiceToDoWithId(idServiceToDo: UUID): ScheduleService {
        TODO("Not yet implemented")
    }
}