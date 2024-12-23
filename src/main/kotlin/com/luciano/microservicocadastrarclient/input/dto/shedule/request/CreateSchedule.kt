package com.luciano.microservicocadastrarclient.input.dto.shedule.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.luciano.microservicocadastrarclient.input.dto.address.CreateAddressClient
import com.luciano.microservicocadastrarclient.input.dto.client.CreateClientUser
import com.luciano.microservicocadastrarclient.input.dto.shedule.response.CollaboratorScheduleResponse
import com.luciano.microservicocadastrarclient.model.Schedule
import com.luciano.microservicocadastrarclient.output.utilenum.ScheduleTask
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val registrationDate: LocalDateTime= LocalDateTime.now(),
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val serviceHours: LocalTime,
    val scheduleTask: ScheduleTask
) {
    fun toEntity(client:CreateClientUser, addressGeneric: CreateAddressClient): Schedule = Schedule(
        idShedule = this.idService,
        description = this.description,
        price = this.price,
        serviceDate = this.serviceDate,
        serviceHours = this.serviceHours,
        scheduleTask = ScheduleTask.PENDING,
        client = client.toEntity(),
        address = addressGeneric.toEntity()
    )
    companion object {
        fun fromEntity(schedule: Schedule): CreateSchedule =
            CreateSchedule(
                idService = schedule.idShedule,
                description = schedule.description,
                price = schedule.price,
                serviceDate = schedule.serviceDate,
                serviceHours = schedule.serviceHours,
                scheduleTask = ScheduleTask.PENDING
            )
    }

}
