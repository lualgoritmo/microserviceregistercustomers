package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.shedule.request.CreateSchedule
import com.luciano.microservicocadastrarclient.input.dto.shedule.response.ScheduleResponse
import com.luciano.microservicocadastrarclient.service.ScheduleService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/schedule")
class ScheduleController(
    private val scheduleService: ScheduleService
) {
    @PostMapping("/create-schedule/{idClient}/{idAddress}/{idCollaborator}")
    fun createShedule(@PathVariable("idClient", required = true) idClient: UUID,
                      @PathVariable("idAddress", required = true) idAddress: UUID,
                      @PathVariable("idCollaborator", required = true) idCollaborator: UUID,
                      @Valid @RequestBody schedule: CreateSchedule
    ): ResponseEntity<ScheduleResponse> {
        val createSchedule = scheduleService.createSchedule(
            schedule = schedule,
            idClint = idClient,
            idAddress = idAddress)
        return ResponseEntity.status(HttpStatus.CREATED).body(ScheduleResponse.fromEntity(createSchedule))
    }
}