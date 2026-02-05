package com.escom.CreadorPracticas.Config.Security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.escom.CreadorPracticas.Entity.Usuario;
import com.escom.CreadorPracticas.Exception.NotAuthenticatedException;
import com.escom.CreadorPracticas.Repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filtro que valida si la peticion tiene la cabezera de Autorizacion
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // de la clase que se extiende garantiza que se ejecute una soola vez

    public final JwtAuthenticationProvider jwtAuthenticationProvider;
    public final UserDetailsService userDetailsService;
    public final UsuarioRepository usuarioRepository;

    private static final List<String> WHITELIST = List.of(
            "/swagger-ui",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/v3/api-docs"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            UsernamePasswordAuthenticationToken token = validateToken(tokenHeader);
            if (token != null) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }

            filterChain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("El Token a caducado");
        }
    }

    public UsernamePasswordAuthenticationToken validateToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        token = token.substring(7);

        try {
            String username = jwtAuthenticationProvider.getUsernameFromToken(token);
            Usuario user = usuarioRepository.findByUsername(username)
                    .orElseThrow(()->new UsernameNotFoundException("El usuario "+username+" no existe."));

            // tenemos que transfrormar el rol de la forma que spring lo reconosca
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRol().name())));

            return new UsernamePasswordAuthenticationToken(
                    new User(user.getUsername(),user.getPassword(),user.isEnabled(),user.isAccountNonExpired(),
                            user.isCredentialsNonExpired(),user.isAccountNonLocked(),authorities),
                    token,
                    authorities
            );


        } catch (JWTDecodeException | NotAuthenticatedException e) {
            // DO NOTHING
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return null;
    }
}