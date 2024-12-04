package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.collaborator.CollaboratorIdsRequest
import com.luciano.microservicocadastrarclient.input.dto.collaborator.CreateCollaborator
import com.luciano.microservicocadastrarclient.service.service.CollaboratorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/collaborator")
class CollaboratorController(
    private val collaboratorService: CollaboratorService
) {

    @PostMapping("/create-collaborator")
    fun createCollaborator(@Valid @RequestBody collaborator: CreateCollaborator):
    ResponseEntity<CreateCollaborator> {
        val response = collaboratorService.createCollaborator(collaborator.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCollaborator.fromEntity(response))
    }

    @PostMapping("/ids-collaborators")
    fun getAllByIdsCollaborators(@Valid @RequestBody idsCollaborator: CollaboratorIdsRequest):
            ResponseEntity<List<CreateCollaborator>> {
        val collaborators = collaboratorService.findAllById(idsCollaborator.ids)
        val responseCollaborator = collaborators.map {
            CreateCollaborator(
                idCollaborator = it.idCollaborator,
                name = it.name,
                dateBirth = it.dateBirth,
                gender = it.gender,
                addressCollaborator = it.addressCollaborator
            )
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseCollaborator)
    }

}
