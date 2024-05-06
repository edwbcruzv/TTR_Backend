package com.escom.CreadorPracticas.Security;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

/**
 * Filtro que valida si la peticion tiene la cabezera de Autorizacion
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // de la clase que se extiende garantiza que se ejecute una soola vez

    public final JwtAuthenticationProvider jwtAuthenticationProvider;
    public final UserDetailsService userDetailsService;
    public final UsuarioRepository usuarioRepository;
    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
        // Extrayendo token de la peticion de la sesion
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = validateToken(tokenHeader);// validacion del token

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        // Token valido, se busca el username en el token
        String username = jwtAuthenticationProvider.getUsernameFromToken(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // buscamos al usuario en la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("Dentro:"+username+userDetails);

            // se valida el Token junto con el usuario
            if (jwtAuthenticationProvider.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((request)));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else{

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
     */

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
            Optional<Usuario> optionalUser = usuarioRepository.findByUsernameIgnoreCase(username);
            if (optionalUser.isEmpty()) {
                throw new NotAuthenticatedException();
            }
            Usuario user = optionalUser.get();
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