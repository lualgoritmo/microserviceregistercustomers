package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_ServiceToDo")
data class ServiceToDo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val idService: UUID?= null,
    private val price: String,
    private val timeService: LocalDateTime,
    @ManyToMany(mappedBy = "schedule", cascade = [CascadeType.ALL])
    @JsonManagedReference
    private val collaborator: List<Collaborator> = listOf()
)

