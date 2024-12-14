package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.model.Schedule
import java.util.UUID

interface ScheduleService {
    fun createScheduleService(schedule: Schedule, idAddress:UUID, idClint:UUID): Schedule
    fun getServiceById(idServiceToDo: UUID): ScheduleService
}