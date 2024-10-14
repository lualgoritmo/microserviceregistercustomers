package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.AddressClient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<AddressClient, Long>