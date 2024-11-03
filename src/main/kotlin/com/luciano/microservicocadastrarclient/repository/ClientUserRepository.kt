package com.luciano.microservicocadastrarclient.repository

import com.luciano.microservicocadastrarclient.model.ClientUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClientUserRepository : JpaRepository<ClientUser, UUID>