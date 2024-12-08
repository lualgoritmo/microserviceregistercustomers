package com.luciano.microservicocadastrarclient.service.service

import com.luciano.microservicocadastrarclient.model.Schedule
import java.util.UUID

interface ScheduleService {
    fun createScheduleService(serviceSchedule: Schedule): Schedule
    fun getServiceById(idServiceToDo: UUID): ScheduleService
}