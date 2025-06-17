package com.luciano.microservicocadastrarclient.security

import com.luciano.microservicocadastrarclient.security.configJWT.JWTAuthenticationFilter
import com.luciano.microservicocadastrarclient.security.configJWT.JWTLoginFilter
import com.luciano.microservicocadastrarclient.security.configJWT.JWTUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtUtil: JWTUtil,
    private val authManager: AuthenticationConfiguration
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/configuration/**"
                    ).permitAll()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.GET,"/v1/collaborator")
                    .hasAuthority("ADMIN")
                    .anyRequest().authenticated()
            }
            // Filtro de login ANTES do UsernamePasswordAuthenticationFilter
            .addFilterBefore(
                JWTLoginFilter(
                    authManager = authenticationManager(authManager),
                    jwtUtil = jwtUtil
                ),
                UsernamePasswordAuthenticationFilter::class.java
            )
            // Filtro de autenticação DEPOIS do UsernamePasswordAuthenticationFilter
            .addFilterAfter(
                jwtAuthenticationFilter(jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        return http.build()
    }

    @Bean
    fun jwtAuthenticationFilter(jwtUtil: JWTUtil): JWTAuthenticationFilter {
        return JWTAuthenticationFilter(jwtUtil)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(
        authConfig: AuthenticationConfiguration
    ): AuthenticationManager = authConfig.authenticationManager

}
