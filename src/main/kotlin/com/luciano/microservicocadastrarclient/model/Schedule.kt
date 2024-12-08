package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.CascadeType
import jakarta.persistence.JoinColumn
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_service_schedule")
data class Schedule(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     val idService: UUID?= null,
     val description: String,
     val price: BigDecimal,
     val serviceDate: LocalDate,
     val serviceHours: LocalDateTime,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JsonManagedReference
    val collaborator: List<Collaborator> = listOf(),

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    val client: ClientUser ?= null
)

