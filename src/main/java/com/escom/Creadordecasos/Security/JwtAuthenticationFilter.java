package com.escom.Creadordecasos.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Filtro que valida si la peticion tiene la cabezera de Autorizacion
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // de la clase que se extiende garantiza que se ejecute una soola vez

    public final JwtAuthenticationProvider jwtAuthenticationProvider;
    public final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
        // Extrayendo token de la peticion de la sesion
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String username;

        String token = validateToken(tokenHeader);// validacion del token

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        // Token valido, se busca el username en el token
        username = jwtAuthenticationProvider.getUsernameFromToken(tokenHeader);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // buscamos al usuario en la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // se valida el Token junto con el usuario
            if (jwtAuthenticationProvider.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((request)));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
            {

                // Token no válido, enviar una respuesta de error (por ejemplo, 401 Unauthorized)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Usuario invalido, probablemente se altero el token.");
            }
        }

        filterChain.doFilter(request, response);
    }

    public String validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        token = token.substring(7);

        return token;
    }
}