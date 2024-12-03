package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.Collaborator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
@Repository
interface CollaboratorRepository: JpaRepository<Collaborator, UUID>