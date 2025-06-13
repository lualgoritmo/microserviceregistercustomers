package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.Collaborator
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Repository
interface CollaboratorRepository : JpaRepository<Collaborator, UUID> {
    @Query("""
    SELECT c FROM Collaborator c WHERE c.idCollaborator NOT IN (
        SELECT cs.idCollaborator FROM Schedule s JOIN s.collaborator cs
        WHERE s.serviceDate = :serviceDate AND s.serviceHours = :serviceHours
    ) 
    """)
    fun findAvailableCollaborators(serviceDate: LocalDate, serviceHours: LocalTime, pageable: Pageable): List<Collaborator>
    fun findByEmail(collaboratorName: String?): Collaborator
}

