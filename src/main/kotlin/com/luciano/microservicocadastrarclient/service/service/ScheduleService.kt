package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.input.dto.shedule.request.CreateSchedule
import com.luciano.microservicocadastrarclient.model.Schedule
import java.util.UUID

interface ScheduleService {
    fun createSchedule(schedule: CreateSchedule, idAddress:UUID, idClint:UUID): Schedule
    fun getServiceById(idServiceToDo: UUID): ScheduleService
}