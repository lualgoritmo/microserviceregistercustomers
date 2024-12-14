package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.Schedule
import com.luciano.microservicocadastrarclient.repository.ServiceToDoRepository
import com.luciano.microservicocadastrarclient.service.service.AddressService
import com.luciano.microservicocadastrarclient.service.service.CadastreClient
import com.luciano.microservicocadastrarclient.service.service.CollaboratorService
import com.luciano.microservicocadastrarclient.service.service.ScheduleService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScheduleServiceImpl(
    private val serviceToDoRepository: ServiceToDoRepository,
    private val clientService: CadastreClient,
    private val collaboratorService: CollaboratorService,
    private val addressService: AddressService
) : ScheduleService {
    @Transactional
    override fun createScheduleService(schedule: Schedule, idAddress: UUID, idClient: UUID): Schedule {
        val client = clientService.getClientById(idClient)

        val address = schedule.addressClient.mapNotNull {
            it.idAddress?.let { address ->
                addressService.getByIdAddress(address)
            }
        }
        val collaborator = schedule.collaborator.mapNotNull { collaborator ->
            collaborator.idCollaborator
        }
        val listCollaborators = collaboratorService.findAllById(collaborator)

        val service = Schedule(
            description = schedule.description,
            price = schedule.price,
            serviceDate = schedule.serviceDate,
            serviceHours = schedule.serviceHours,
            collaborator = listCollaborators,
            client = client,
            addressClient = address
        )
        return serviceToDoRepository.save(service)
    }

    override fun getServiceById(idServiceToDo: UUID): ScheduleService {
        TODO("Not yet implemented")
    }
}