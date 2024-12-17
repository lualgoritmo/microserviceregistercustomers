package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.model.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.UUID
@Repository
interface ServiceToDoRepository: JpaRepository<Schedule, UUID> {
    fun findByClientAndAddressAndServiceDate(
        client: ClientUser,
        address: AddressGeneric,
        serviceDate: LocalDate
    ): Schedule?
}