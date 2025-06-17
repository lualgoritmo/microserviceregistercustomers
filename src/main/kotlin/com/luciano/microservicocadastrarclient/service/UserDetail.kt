package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.entity.Collaborator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val collaborator: Collaborator): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        collaborator.role.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    override fun getPassword(): String = collaborator.password

    override fun getUsername(): String = collaborator.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}