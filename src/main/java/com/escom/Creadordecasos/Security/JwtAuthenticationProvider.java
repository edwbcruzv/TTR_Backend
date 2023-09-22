package com.escom.Creadordecasos.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.escom.Creadordecasos.Entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Clase encargada de la creación y validación de jwt para el inicio de sesión de Usuario
 */
@Component
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
    public String createToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + (timeMinutes * 60 * 1000));
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .withClaim("rol", user.getRol())
                .withIssuer(issuer)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    /**
     * Obtiene el username dado un token
     *
     * @param token Token del que se va a extraer el username
     * @return Username extraido
     */
    public String getUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return jwt.getClaim("username").asString();
    }
}
