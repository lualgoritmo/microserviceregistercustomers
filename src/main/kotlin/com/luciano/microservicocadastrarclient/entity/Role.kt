package com.luciano.microservicocadastrarclient.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import java.util.UUID

@Entity
@Table(name = "role")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID?= null,
    val name: String
): GrantedAuthority {
    override fun getAuthority(): String = name
}
