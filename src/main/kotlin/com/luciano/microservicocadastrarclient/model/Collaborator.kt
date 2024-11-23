package com.luciano.microservicocadastrarclient.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID
@Entity
@Table(name = "tb_collaborator")
data class Collaborator(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idCollaborator: UUID ? = null,
    val name: String,
    val dateBirth: LocalDateTime,
    val gender: String,
    @ManyToMany(mappedBy = "collaborator", cascade = [CascadeType.ALL])
    @JsonManagedReference
    val clientUser: List<ClientUser> = listOf(),

    @OneToMany(mappedBy = "collaborator", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val addressCollaborator: List<AddressGeneric>,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "id_serviceToDo")
    @JsonBackReference
    val serviceToDo: MutableSet<ServiceToDo> = mutableSetOf()

)
