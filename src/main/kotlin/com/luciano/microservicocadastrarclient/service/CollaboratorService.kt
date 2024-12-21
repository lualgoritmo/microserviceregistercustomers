package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.model.Collaborator
import java.util.UUID

interface CollaboratorService {
    fun createCollaborator(collaborator: Collaborator): Collaborator
    fun getCollaboratorWithId(idCollaborator: UUID): Collaborator
    fun findAllById(idsCollaborator: List<UUID>): List<Collaborator>
    fun findByIdCollaborator(idCollaborator: UUID): Collaborator
}