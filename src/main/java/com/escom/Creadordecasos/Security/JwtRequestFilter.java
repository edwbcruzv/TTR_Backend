package com.escom.Creadordecasos.Security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.escom.Creadordecasos.Entity.User;
import com.escom.Creadordecasos.Exception.NotAuthenticatedException;
import com.escom.Creadordecasos.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

/**
 * Filtro que valida si la peticion tiene la cabezera de Autorizacion
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private UserRepository userRepository;

    /**
     * Valida si la petición contiene la cabezera de authorization con el bearer token
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        UsernamePasswordAuthenticationToken token = validateToken(tokenHeader);
        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        token = token.substring(7);
        try {
            String username = jwtAuthenticationProvider.getUsername(token);
            Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(username);
            if (optionalUser.isEmpty()) {
                throw new NotAuthenticatedException();
            }
            User user = optionalUser.get();
            HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
            rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));


            return new UsernamePasswordAuthenticationToken(
                    user,
                    token,
                    rolesAndAuthorities
            );


        } catch (JWTDecodeException | NotAuthenticatedException e) {
            // DO NOTHING
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return null;
    }
}