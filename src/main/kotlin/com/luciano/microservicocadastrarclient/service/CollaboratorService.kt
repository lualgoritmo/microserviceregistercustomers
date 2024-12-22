package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.model.Collaborator
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

interface CollaboratorService {
    fun createCollaborator(collaborator: Collaborator): Collaborator
    fun getCollaboratorWithId(idCollaborator: UUID): Collaborator
    fun findAllById(idsCollaborator: List<UUID>): List<Collaborator>

    fun findAvailableCollaborators(serviceDate: LocalDate, serviceHours: LocalTime, pageAble: Pageable): List<Collaborator>
    fun findByIdCollaborator(idCollaborator: UUID): Collaborator
}