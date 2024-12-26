package comapigateway.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import comapigateway.config.security.UserPrincipal; // Clase personalizada que representa los detalles del usuario autenticado.
import comapigateway.entities.User; // Clase que representa la entidad User en el sistema.
import comapigateway.utils.SecurityUtils; // Clase de utilidades para manejo de seguridad.
import io.jsonwebtoken.Claims; // Representa los claims (información) dentro de un JWT.
import io.jsonwebtoken.Jwts; // Clase para trabajar con JWT.
import io.jsonwebtoken.SignatureAlgorithm; // Algoritmo de firma para JWT.
import io.jsonwebtoken.security.Keys; // Herramientas para manejar claves de seguridad.

@Component // Declara esta clase como un componente de Spring para inyección de dependencias.
public class JwtProviderImpl implements JwtProvider {

    // Secreto JWT configurado en el archivo de propiedades.
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    // Duración del token en milisegundos configurada en el archivo de propiedades.
    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    /**
     * Genera un token JWT basado en los detalles de un usuario autenticado.
     *
     * @param auth Objeto UserPrincipal que contiene información del usuario.
     * @return Token JWT generado.
     */
    @Override
    public String generateToken(UserPrincipal auth) {
        // Convierte los roles del usuario en una cadena separada por comas.
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Crea la clave de firma usando el secreto configurado.
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // Construye y firma el token JWT.
        return Jwts.builder()
                .setSubject(auth.getUsername()) // Establece el nombre de usuario como sujeto.
                .claim("roles", authorities) // Agrega los roles como claim.
                .claim("userId", auth.getId()) // Agrega el ID del usuario como claim.
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS)) // Fecha de expiración.
                .signWith(key, SignatureAlgorithm.HS512) // Firma con el algoritmo HMAC SHA-512.
                .compact();
    }

    /**
     * Genera un token JWT basado en una entidad User.
     *
     * @param user Entidad User que contiene información del usuario.
     * @return Token JWT generado.
     */
    @Override
    public String generateToken(User user) {
        // Crea la clave de firma usando el secreto configurado.
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // Construye y firma el token JWT.
        return Jwts.builder()
                .setSubject(user.getUsername()) // Establece el nombre de usuario como sujeto.
                .claim("roles", user.getRole()) // Agrega los roles como claim.
                .claim("userId", user.getId()) // Agrega el ID del usuario como claim.
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS)) // Fecha de expiración.
                .signWith(key, SignatureAlgorithm.HS512) // Firma con el algoritmo HMAC SHA-512.
                .compact();
    }

    /**
     * Recupera un objeto de autenticación a partir de una solicitud HTTP.
     *
     * @param request Solicitud HTTP que contiene el token JWT.
     * @return Objeto Authentication o null si no es válido.
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        // Extrae los claims del JWT presente en la solicitud.
        Claims claims = extractClaims(request);
        if (claims == null) {
            return null; // Devuelve null si no se pueden extraer las claims.
        }

        // Obtiene el nombre de usuario y el ID del usuario desde las claims.
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        // Convierte los roles en una lista de GrantedAuthority.
        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        // Crea un objeto UserPrincipal con los detalles del usuario.
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUsername(username);
        userPrincipal.setAuthorities(authorities);
        userPrincipal.setId(userId);

        // Si el nombre de usuario es nulo, no se puede autenticar.
        if (username == null) {
            return null;
        }

        // Devuelve un objeto Authentication con los detalles del usuario.
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
    }

    /**
     * Verifica si un token JWT presente en la solicitud es válido.
     *
     * @param request Solicitud HTTP que contiene el token JWT.
     * @return true si el token es válido, false en caso contrario.
     */
    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        // Extrae los claims del JWT.
        Claims claims = extractClaims(request);
        if (claims == null) {
            return false; // El token no es válido si no se pueden extraer las claims.
        }

        // Verifica si la fecha de expiración es anterior a la fecha actual.
        return !claims.getExpiration().before(new Date());
    }

    /**
     * Extrae los claims de un token JWT presente en la solicitud HTTP.
     *
     * @param request Solicitud HTTP que contiene el token JWT.
     * @return Objeto Claims o null si no se puede extraer.
     */
    private Claims extractClaims(HttpServletRequest request) {
        // Extrae el token desde la cabecera Authorization.
        String token = SecurityUtils.extractAuthTokenFromRequest(request);

        if (token == null) {
            return null; // Devuelve null si el token no está presente.
        }

        // Crea la clave de firma usando el secreto configurado.
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // Devuelve los claims extraídos del token.
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
