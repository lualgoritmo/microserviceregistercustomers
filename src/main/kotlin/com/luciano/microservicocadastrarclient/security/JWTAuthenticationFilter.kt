package com.luciano.microservicocadastrarclient.security

import com.luciano.microservicocadastrarclient.config.configJWT.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.requestURI
        // Ignorar rotas do Swagger
        if (
            path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs") ||
            path.startsWith("/swagger-resources") ||
            path.startsWith("/webjars") ||
            path.startsWith("/configuration")
        ) {
            filterChain.doFilter(request, response)
            return
        }

        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        println("Filtro JWT chamado! Token header: $token, JWT extraído: $jwt")

        if (jwt == null) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Unauthorized")
            response.writer.flush()
            return
        }

        try {
            if (jwtUtil.isValid(jwt)) {
                val authentication = jwtUtil.getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication
                filterChain.doFilter(request, response)
            } else {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("Unauthorized")
                response.writer.flush()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.writer.write("Internal server error: " + ex.message)
            response.writer.flush()
        }
    }

    private fun getTokenDetail(token: String?): String? {
        return if (token != null && token.startsWith("Bearer ")) {
            token.substring(7)
        } else {
            null
        }
    }
}
