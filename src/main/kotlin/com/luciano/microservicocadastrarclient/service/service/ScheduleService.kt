package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.model.ServiceSchedule
import java.util.UUID

interface ScheduleService {
    fun createScheduleService(serviceSchedule: ServiceSchedule): ServiceSchedule
    fun getServiceToDoWithId(idServiceToDo: UUID): ScheduleService
}