package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.shedule.request.CreateSchedule
import com.luciano.microservicocadastrarclient.service.service.ScheduleService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/schedule")
class SheduleController(
    private val sheduleService: ScheduleService
) {
    @PostMapping("/create-shedule")
    fun createShedule(@Valid @RequestBody schedule: CreateSchedule): ResponseEntity<CreateSchedule> {
        val scheduleEntity = schedule.toEntity()
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateSchedule.fromEntity(scheduleEntity))
    }
}