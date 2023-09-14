package com.escom.Creadordecasos.Config.Security.Filter;

import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Repository.UserRepository;
import com.escom.Creadordecasos.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain ) throws ServletException, IOException {
        // 1. Obtener el header que contiene el jwt
        String authHeader = request.getHeader( "Authorization" );
        if(authHeader == null || !authHeader.startsWith( "Bearer " )) {
            filterChain.doFilter( request, response );
            return;
        }

        // 2. Obtener el jwt del header
        String jwt = authHeader.split( " " )[1];

        // 3. Obtener el subject del jwt
        String username = jwtService.extractUsername(jwt);

        // 4. Settear un objeto Authentication(la instancia del usuario loggeado) dentro del SecurityContext
        User user = userRepository.findByUsername( username ).get();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication( authToken );

        // 5. Ejecutar el resto de filtros
        filterChain.doFilter( request, response );
    }
}
