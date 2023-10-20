package com.escom.Creadordecasos.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Filtro que valida si la peticion tiene la cabezera de Autorizacion
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // de la clase que se extiende garantiza que se ejecute una soola vez

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
        // Extrayendo token de la peticion
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token = validateToken(tokenHeader);
        if (token != null) {
            // Token válido, continuar con la cadena de filtros
            filterChain.doFilter(request, response);
        } else {
            // Token no válido, enviar una respuesta de error (por ejemplo, 401 Unauthorized)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token no válido");
        }
    }

    public String validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        token = token.substring(7);

        return token;
    }
}