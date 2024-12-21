package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.input.dto.shedule.request.CreateSchedule
import com.luciano.microservicocadastrarclient.model.Schedule
import com.luciano.microservicocadastrarclient.repository.ServiceToDoRepository
import com.luciano.microservicocadastrarclient.service.AddressService
import com.luciano.microservicocadastrarclient.service.CadastreClient
import com.luciano.microservicocadastrarclient.service.CollaboratorService
import com.luciano.microservicocadastrarclient.service.ScheduleService
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
    override fun createSchedule(scheduleDTO: CreateSchedule, idAddress: UUID, idClient: UUID): Schedule {
        val client = clientService.getClientById(idClient)

        val address = addressService.getByIdAddress(idAddress)
        val existingSchedule = serviceToDoRepository
            .findByClientAndAddressAndServiceDate(
                client = client,
                address = address,
                serviceDate = scheduleDTO.serviceDate
            )

        if (existingSchedule != null) {
            throw RuntimeException("Já existe um agendamento para este cliente no mesmo endereço e data.")
        }

        val listCollaborators = scheduleDTO.collaborators.let { collaboratorService.findAllById(it) }

        if(listCollaborators.isEmpty()) {
            throw RuntimeException("Lista de colaboradores vazia")
        }

        val service = Schedule(
            description = scheduleDTO.description,
            price = scheduleDTO.price,
            serviceDate = scheduleDTO.serviceDate,
            serviceHours = scheduleDTO.serviceHours,
            collaborator = listCollaborators,
            client = client,
            address = address
        )
        return serviceToDoRepository.save(service)
    }
    override fun getServiceById(idServiceToDo: UUID): Schedule {
        return serviceToDoRepository.findById(idServiceToDo).orElseThrow {
            throw RuntimeException("O id do serviço não existe $idServiceToDo")
        }
    }
}
