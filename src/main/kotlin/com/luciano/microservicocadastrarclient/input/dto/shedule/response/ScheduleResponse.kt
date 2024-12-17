package com.luciano.microservicocadastrarclient.input.dto.shedule.response

import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddressClient
import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.input.dto.collaborator.CreateCollaborator
import com.luciano.microservicocadastrarclient.model.Schedule
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class ScheduleResponse(
    val idService: UUID,
    val description: String,
    val price: BigDecimal,
    val serviceDate: LocalDate,
    val serviceHours: LocalTime,
    val client: CreateClientUser,
    val addressClient: CreateAddressClient,
    val collaborators: List<CreateCollaborator>
) {
    companion object {
        fun fromEntity(schedule: Schedule): ScheduleResponse = ScheduleResponse(
            idService = schedule.idShedule!!,
            description = schedule.description,
            price = schedule.price,
            serviceDate = schedule.serviceDate,
            serviceHours = schedule.serviceHours,
            client = CreateClientUser.fromEntity(schedule.client),
            addressClient = CreateAddressClient.fromEntity(schedule.address),
            collaborators = schedule.collaborator.map { CreateCollaborator.fromEntity(it) }
        )
    }
}

