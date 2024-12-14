package com.luciano.microservicocadastrarclient.model

import jakarta.persistence.*
import lombok.EqualsAndHashCode
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_service_schedule")
@EqualsAndHashCode(of = ["idShedule"])
data class Schedule(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     val idShedule: UUID?= null,
    val description: String,
    val price: BigDecimal,
    val serviceDate: LocalDate,
    val serviceHours: LocalDateTime,

    @ManyToMany(cascade = [CascadeType.ALL])
    val collaborator: List<Collaborator> = listOf(),

    @ManyToOne
    @JoinColumn(name = "id_client")
    val client: ClientUser,

    @OneToOne
    @JoinColumn(name = "id_address")
    val addressClient: List<AddressGeneric> = listOf()
)

