package com.luciano.microservicocadastrarclient.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import java.util.UUID

@Entity
@Table(name = "role")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID?= null,
    val nome: String
): GrantedAuthority {
    override fun getAuthority(): String = nome
}
