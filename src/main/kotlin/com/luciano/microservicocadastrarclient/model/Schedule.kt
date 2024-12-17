package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Entity
@Table(name = "tb_service_schedule")
@EqualsAndHashCode(of = ["idShedule"])
data class Schedule(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idShedule: UUID? = null,
    val description: String,
    val price: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    val serviceDate: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val serviceHours: LocalTime,

    @ManyToMany(mappedBy = "schedule", cascade = [CascadeType.ALL])
    @JsonManagedReference("scheduleReference")
    val collaborator: List<Collaborator> = listOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    val client: ClientUser,

    @OneToOne
    @JoinColumn(name = "id_address")
    val address: AddressGeneric
)

