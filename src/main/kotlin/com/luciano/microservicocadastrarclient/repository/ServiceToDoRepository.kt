package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.model.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
@Repository
interface ServiceToDoRepository: JpaRepository<Schedule, UUID> {
    fun findByClientAndAddressAndServiceDateAndServiceHours(
        client: ClientUser,
        address: AddressGeneric,
        serviceDate: LocalDate,
        serviceHours: LocalTime
    ): Schedule?

    @Query("""
    SELECT COUNT(s) > 0 FROM Schedule s WHERE s.client = :client AND s.address = :address 
      AND s.serviceDate = :serviceDate AND s.serviceHours = :serviceHours""")
    fun existsByClientAndAddressAndDateAndTime(client: ClientUser, address: AddressGeneric, serviceDate: LocalDate,
        serviceHours: LocalTime
    ): Boolean

}
