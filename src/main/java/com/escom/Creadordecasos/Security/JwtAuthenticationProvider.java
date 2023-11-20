package com.escom.Creadordecasos.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.escom.Creadordecasos.Entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

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
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .withClaim("rol", user.getRol())
                .withIssuer(issuer)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);

        System.out.println("Token creado: " + token);

        return token;
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token,"username");
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        DecodedJWT jwt = allClaims(token);
        String username = getUsernameFromToken(token);
        // Se verifica la fecha de expiracion con el momento que ce hace la peticion.
        return (jwt.getExpiresAt().before(new Date()) && username.equals(userDetails.getUsername()));
    }

    public String getClaim(String token, String claim){
        return allClaims(token).getClaim(claim).asString();

    }

    private DecodedJWT allClaims(String token){
        System.out.println("Decodificando token: " + token);
        try {
            JWT.decode(token).getClaims();
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        }catch (JWTDecodeException e) {
            // Agregar log para imprimir detalles sobre la excepción
            System.err.println("Error al decodificar el token: " + e.getMessage());
            throw e;
        }
    }
}
