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

import comapigateway.config.security.UserPrincipal;
import comapigateway.entities.User;
import comapigateway.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component // Marca esta clase como un bean de Spring, lo que permite su inyección en otras partes de la aplicación
public class JwtProviderImpl implements JwtProvider {

    // Inyección del secreto JWT desde el archivo de propiedades
	@Value("${app.jwt.secret}")
	private String JWT_SECRET;

    // Inyección de la duración de expiración del JWT en milisegundos desde el archivo de propiedades
	@Value("${app.jwt.expiration-in-ms}")
	private Long JWT_EXPIRATION_IN_MS;

    // Método para generar un token JWT basado en un UserPrincipal
	@Override
	public String generateToken(UserPrincipal auth) {
        // Obtiene los roles o autoridades del usuario y los convierte a una cadena separada por comas
		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

        // Genera una clave de cifrado usando el secreto JWT y el algoritmo HMAC SHA-512
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // Crea y devuelve un JWT usando el nombre de usuario, los roles, el ID de usuario y la fecha de expiración
		return Jwts.builder().setSubject(auth.getUsername()).claim("roles", authorities).claim("userId", auth.getId())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
				.signWith(key, SignatureAlgorithm.HS512).compact();

	}

    // Sobrecarga del método `generateToken` para generar un JWT basado en un objeto `User`
	@Override
	public String generateToken(User user) {
        // Genera una clave de cifrado usando el secreto JWT y el algoritmo HMAC SHA-512
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // Crea y devuelve un JWT usando el nombre de usuario, los roles, el ID de usuario y la fecha de expiración
		return Jwts.builder().setSubject(user.getUsername()).claim("roles", user.getRole())
				.claim("userId", user.getId())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
				.signWith(key, SignatureAlgorithm.HS512).compact();
	}

    // Método para obtener la autenticación basada en el JWT extraído de una solicitud HTTP
	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
        // Extrae las claims (reclamaciones) del JWT de la solicitud
		Claims claims = extractClaims(request);
		if (claims == null) {
            // Si no se pueden extraer las claims, devuelve null
			return null;
		}

        // Obtiene el nombre de usuario y el ID de usuario desde las claims
		String username = claims.getSubject();
		Long userId = claims.get("userId", Long.class);

        // Convierte los roles (almacenados como una cadena separada por comas) en un conjunto de GrantedAuthority
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtils::convertToAuthority).collect(Collectors.toSet());

        // Crea un objeto UserPrincipal y asigna el nombre de usuario, ID y roles
		UserPrincipal userPrincipal = new UserPrincipal();
		userPrincipal.setUsername(username);
		userPrincipal.setAuthorities(authorities);
		userPrincipal.setId(userId);

        // Si no hay un nombre de usuario, devuelve null
		if (username == null) {
			return null;
		}

        // Devuelve un token de autenticación con el UserPrincipal y sus roles
		return new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
	}

    // Método para verificar si un token es válido
	@Override
	public boolean isTokenValid(HttpServletRequest request) {
        // Extrae las claims del JWT desde la solicitud
		Claims claims = extractClaims(request);
		if (claims == null) {
            // Si no se pueden extraer las claims, el token no es válido
			return false;
		}

        // Verifica si la fecha de expiración del token es anterior a la fecha actual
		if (claims.getExpiration().before(new Date())) {
            // Si el token ha expirado, no es válido
			return false;
		}

        // Si las claims existen y no ha expirado, el token es válido
		return true;
	}

    // Método privado para extraer las claims desde un JWT en la solicitud HTTP
	private Claims extractClaims(HttpServletRequest request) {
        // Extrae el token JWT desde la cabecera de autorización de la solicitud
		String token = SecurityUtils.extractAuthTokenFromRequest(request);

		if (token == null) {
            // Si no se encuentra el token, devuelve null
			return null;
		}

        // Genera una clave de cifrado usando el secreto JWT y el algoritmo HMAC SHA-512
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // Devuelve las claims extraídas del token usando la clave de cifrado
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

}
