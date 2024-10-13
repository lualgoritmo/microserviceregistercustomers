package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.AddressClient
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AddressClient : JpaRepository<AddressClient, UUID>