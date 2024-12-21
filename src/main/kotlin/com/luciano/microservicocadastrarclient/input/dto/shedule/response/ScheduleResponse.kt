package com.luciano.microservicocadastrarclient.input.dto.shedule.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.model.Schedule
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class ScheduleResponse(
    val idService: UUID,
    val description: String,
    val price: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val serviceDate: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val serviceHours: LocalTime,
    val client: CreateClientUser,
    val collaborators: List<CollaboratorScheduleResponse>
) {
    companion object {
        fun fromEntity(schedule: Schedule): ScheduleResponse = ScheduleResponse(
            idService = schedule.idShedule!!,
            description = schedule.description,
            price = schedule.price,
            serviceDate = schedule.serviceDate,
            serviceHours = schedule.serviceHours,
            client = CreateClientUser.fromEntity(schedule.client),
            collaborators = schedule.collaborator.map { CollaboratorScheduleResponse.fromEntity(it) }
        )
    }

}
