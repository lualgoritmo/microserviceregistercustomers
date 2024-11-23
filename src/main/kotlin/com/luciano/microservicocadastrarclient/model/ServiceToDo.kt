package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.CascadeType
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_service")
data class ServiceToDo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val idService: UUID?= null,
    private val price: String,
    private val timeService: LocalDateTime,
    @ManyToMany(mappedBy = "serviceToDo", cascade = [CascadeType.ALL])
    @JsonManagedReference
    private val collaborator: List<Collaborator> = listOf()
)

