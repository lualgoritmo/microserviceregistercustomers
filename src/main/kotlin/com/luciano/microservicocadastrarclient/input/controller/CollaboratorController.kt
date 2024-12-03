package com.luciano.microservicocadastrarclient.input.controller

import com.luciano.microservicocadastrarclient.input.dto.collaborator.CreateCollaborator
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/collaborator")
class CollaboratorController {

    @RequestMapping("/v1/create-collaborator")
    fun createCollaborator(@Valid @RequestBody collaborator: CreateCollaborator):
    ResponseEntity<CreateCollaborator> {
        return ResponseEntity.status(HttpStatus.CREATED).body(collaborator)
    }

}
