package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.collaborator.CollaboratorIdsRequest
import com.luciano.microservicocadastrarclient.input.dto.collaborator.CreateCollaborator
import com.luciano.microservicocadastrarclient.service.CollaboratorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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

    @GetMapping("/{id}")
    fun getCollaboratorById(@PathVariable id: UUID):ResponseEntity<CreateCollaborator> {
        val idCollaborator = collaboratorService.getCollaboratorWithId(id)
        return ResponseEntity.status(HttpStatus.OK).body(CreateCollaborator.fromEntity(idCollaborator))
    }
    @PostMapping("/ids-collaborators")
    fun getAllByIdsCollaborators(@Valid @RequestBody idsCollaborator: CollaboratorIdsRequest):
            ResponseEntity<List<CreateCollaborator>> {
        val collaborators = collaboratorService.findAllById(idsCollaborator.ids)
        val responseCollaborator = CreateCollaborator.fromListCollaborators(collaborators)
        return ResponseEntity.status(HttpStatus.OK).body(responseCollaborator)
    }

}
