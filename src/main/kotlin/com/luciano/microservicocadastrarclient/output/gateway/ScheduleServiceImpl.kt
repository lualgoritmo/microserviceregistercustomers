package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.ServiceSchedule
import com.luciano.microservicocadastrarclient.repository.ServiceToDoRepository
import com.luciano.microservicocadastrarclient.service.service.ScheduleService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ScheduleServiceImpl(private val serviceToDoRepository: ServiceToDoRepository): ScheduleService {
    override fun createToDoService(serviceSchedule: ServiceSchedule) =
        serviceToDoRepository.save(serviceSchedule)

    override fun getServiceToDoWithId(idServiceToDo: UUID): ScheduleService {
        TODO("Not yet implemented")
    }
}