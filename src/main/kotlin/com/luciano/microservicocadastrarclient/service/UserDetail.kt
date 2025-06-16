package com.luciano.microservicocadastrarclient.service

import com.luciano.microservicocadastrarclient.entity.Collaborator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val collaborator: Collaborator): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = collaborator.roles

    override fun getPassword(): String = collaborator.password

    override fun getUsername(): String = collaborator.email

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

}