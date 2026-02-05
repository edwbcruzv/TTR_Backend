package com.escom.CreadorPracticas.Config.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.escom.CreadorPracticas.Entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Clase encargada de la creación y validación de jwt para el inicio de sesión de Usuario
 */
@Service
public class JwtAuthenticationProvider {

    /**
     * Llave para cifrar el jwt
     */
    @Value("${security.jwt.key}")
    private String secretKey;

    /**
     * Duración del jwt en minutos
     */
    @Value("${security.jwt.minutes}")
    private Integer timeMinutes;

    /**
     * Emisor del token
     */
    @Value("${security.jwt.issuer}")
    private String issuer;

    /**
     * Genera un nuevo token dado un usuario
     *
     * @param user Usuario con el cual se genera el token
     * @return Token generado
     */
    public String createToken(Usuario user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + (timeMinutes * 60 * 1000));
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String token = JWT.create()
                .withClaim("username", user.getUsername())
                .withClaim("nombreCompleto", user.getNombre()+" "+user.getApellidoMaterno()+" "+user.getApellidoPaterno())
                .withClaim("email", user.getEmail())
                .withClaim("rol", user.getRol().name())
                .withIssuer(issuer)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);

        System.out.println("Token creado: " + token);

        return token;
    }

    public String generateTemporalToken(String username, long expirationMillis) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String token = JWT.create()
                .withClaim("username", username)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);

        return token;
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, "username");
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        DecodedJWT jwt = allClaims(token);
        String username = getUsernameFromToken(token);
        // Se verifica la fecha de expiracion con el momento que ce hace la peticion.
        return (jwt.getExpiresAt().before(new Date()) && username.equals(userDetails.getUsername()));
    }

    public String getClaim(String token, String claim) {
        return allClaims(token) != null ? allClaims(token).getClaim(claim).asString() : null;

    }


    private DecodedJWT allClaims(String token) {
        System.out.println("Decodificando token: " + token);
        try {
            JWT.decode(token).getClaims();
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        } catch (JWTDecodeException e) {
            // Agregar log para imprimir detalles sobre la excepción
            System.err.println("Error al decodificar el token: " + e.getMessage());
            return null;
        } catch (MissingClaimException e) {
            System.err.println("Error al obtener claim: " + e.getMessage());
            return null;
        }
    }

    public Long validateTemporalToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

        // Verificar que el token no haya expirado
        if (jwt.getExpiresAt().before(new Date())) {
            return null; // Token expirado
        }

        // Obtener el valor del reclamo "id" y devolverlo
        Long userId = jwt.getClaim("id").asLong();

        return userId;
    }


}
