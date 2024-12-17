package com.luciano.microservicocadastrarclient.input.dto.shedule.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddressClient
import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.input.dto.collaborator.CreateCollaborator
import com.luciano.microservicocadastrarclient.model.Schedule
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

data class CreateSchedule(
    val idService: UUID? = null,
    @NotBlank
    val description: String,
    @NotBlank
    val price: BigDecimal,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val serviceDate: LocalDate,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val serviceHours: LocalTime,
    val client: UUID,
    val addressClient: UUID,
    val collaborators: List<UUID> = listOf()
) {

    fun toEntity(client:CreateClientUser, addressGeneric: CreateAddressClient, collaborators: List<CreateCollaborator>): Schedule = Schedule(
        idShedule = this.idService,
        description = this.description,
        price = this.price,
        serviceDate = this.serviceDate,
        serviceHours = this.serviceHours,
        client = client.toEntity(),
        address = addressGeneric.toEntity(),
        collaborator = collaborators.map { it.toEntity() }
    )
    companion object {
        fun fromEntity(schedule: Schedule): CreateSchedule =
            CreateSchedule(
                idService = schedule.idShedule,
                description = schedule.description,
                price = schedule.price,
                serviceDate = schedule.serviceDate,
                serviceHours = schedule.serviceHours,
                client = schedule.client.idClientUser ?: throw IllegalArgumentException("Client id não pode ser nulo"),
                addressClient = schedule.address.idAddress ?: throw IllegalArgumentException("IdAddress não pode ser nulo"),
                collaborators = schedule.collaborator.map { it.idCollaborator ?: throw IllegalArgumentException("Colaboraor id não pode ser nulo") }
            )
    }

}


