package com.luciano.microservicocadastrarclient.repository

import jakarta.ws.rs.client.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository : JpaRepository<Client, UUID>