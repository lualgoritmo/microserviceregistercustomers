package com.luciano.microservicocadastrarclient.input.dto.shedule.request

import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.model.Collaborator
import com.luciano.microservicocadastrarclient.model.Schedule
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class CreateSchedule(
    val idService: UUID?= null,
    @NotBlank
    val description: String,
    @NotBlank
    val price: BigDecimal,
    @NotBlank
    val serviceDate: LocalDate,
    @NotBlank
    val serviceHours: LocalDateTime,
    @NotBlank(message = "Colaborador não pode ser vazio")
    val collaborator: List<Collaborator> = listOf(),
    val client: ClientUser? = null
) {

    fun toEntity(): Schedule = Schedule(
        idShedule = this.idService,
        description = this.description,
        price = this.price,
        serviceDate = this.serviceDate,
        serviceHours = this.serviceHours,
        collaborator = this.collaborator,
        client = this.client
    )
    companion object {
        fun fromEntity(schedule: Schedule): CreateSchedule =
            CreateSchedule(
                idService = schedule.idShedule,
                description = schedule.description,
                price = schedule.price,
                serviceDate = schedule.serviceDate,
                serviceHours = schedule.serviceHours,
                collaborator = schedule.collaborator,
                client = schedule.client
            )

        fun fromListEntity(schedules: List<Schedule>, collaborators: List<Collaborator>): List<CreateSchedule> =
            schedules.map { schedule ->
                CreateSchedule(
                    idService = schedule.idShedule,
                    description = schedule.description,
                    price = schedule.price,
                    serviceDate = schedule.serviceDate,
                    serviceHours = schedule.serviceHours,
                    collaborator = collaborators,
                    client = schedule.client
                )
            }
    }
}
