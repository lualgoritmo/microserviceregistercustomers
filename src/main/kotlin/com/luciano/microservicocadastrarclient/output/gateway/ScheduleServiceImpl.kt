package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.input.dto.shedule.request.CreateSchedule
import com.luciano.microservicocadastrarclient.model.Schedule
import com.luciano.microservicocadastrarclient.output.utilenum.ScheduleTask
import com.luciano.microservicocadastrarclient.output.utilenum.ServiceStatus.MAX_COLLABORATORS
import com.luciano.microservicocadastrarclient.repository.ServiceToDoRepository
import com.luciano.microservicocadastrarclient.service.AddressService
import com.luciano.microservicocadastrarclient.service.CadastreClient
import com.luciano.microservicocadastrarclient.service.CollaboratorService
import com.luciano.microservicocadastrarclient.service.ScheduleService
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
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
        if(serviceToDoRepository.existsByClientAndAddressAndDateAndTime(
            client = client,
            address = address,
            serviceDate = scheduleDTO.serviceDate,
            serviceHours = scheduleDTO.serviceHours
        )) {
            throw RuntimeException("Já existe um agendamento para este cliente no mesmo endereço, data e horário.")
        }

//        val existingSchedule = serviceToDoRepository
//            .findByClientAndAddressAndServiceDateAndServiceHours(
//                client = client,
//                address = address,
//                serviceDate = scheduleDTO.serviceDate,
//                serviceHours = scheduleDTO.serviceHours
//            )

        val listCollaborators = collaboratorService.findAvailableCollaborators(
            serviceDate = scheduleDTO.serviceDate,
            serviceHours = scheduleDTO.serviceHours,
            pageAble = PageRequest.of(0, Int.MAX_VALUE)
        )

        if(listCollaborators.size < MAX_COLLABORATORS) {
            throw RuntimeException("Nenhum colaborador disponível para essa data e horário")
        }
        val selectedCollaborator = listCollaborators.take(MAX_COLLABORATORS)

        val service = Schedule(
            description = scheduleDTO.description,
            price = scheduleDTO.price,
            serviceDate = scheduleDTO.serviceDate,
            serviceHours = scheduleDTO.serviceHours,
            scheduleTask = ScheduleTask.PENDING,
            collaborator = selectedCollaborator,
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
