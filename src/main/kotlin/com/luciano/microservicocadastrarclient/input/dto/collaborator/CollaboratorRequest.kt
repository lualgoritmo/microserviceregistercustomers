package com.luciano.microservicocadastrarclient.input.dto.collaborator

import java.util.UUID
data class CollaboratorIdsRequest(
    val ids: List<UUID>
)
