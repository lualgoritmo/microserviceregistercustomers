package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.Collaborator
import com.luciano.microservicocadastrarclient.repository.CollaboratorRepository
import com.luciano.microservicocadastrarclient.service.service.CollaboratorService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CollaboratorServiceImpl(private val collaboratorRepository: CollaboratorRepository): CollaboratorService {
    override fun createCollaborator(collaborator: Collaborator) = collaboratorRepository.save(collaborator)
    override fun getCollaboratorWithId(idCollaborator: UUID): Collaborator {
        TODO("Not yet implemented")
    }
}