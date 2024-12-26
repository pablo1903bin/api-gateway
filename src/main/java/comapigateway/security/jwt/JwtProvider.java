package comapigateway.security.jwt;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import comapigateway.config.security.UserPrincipal; // Clase personalizada que representa los detalles del usuario autenticado.
import comapigateway.entities.User; // Clase que representa la entidad de usuario en mi sistema.

// Interfaz para manejar la lógica de generación y validación de tokens JWT.
public interface JwtProvider {

    /**
     * Genera un token JWT basado en un objeto UserPrincipal.
     * Este método es útil para generar tokens para usuarios autenticados.
     *
     * @param auth - El objeto UserPrincipal que contiene detalles del usuario autenticado.
     * @return Un token JWT en forma de cadena.
     */
    String generateToken(UserPrincipal auth);

    /**
     * Genera un token JWT basado en la entidad User.
     * Este método es útil si necesitas generar un token directamente desde una entidad de usuario.
     *
     * @param user - La entidad User que contiene la información del usuario.
     * @return Un token JWT en forma de cadena.
     */
    String generateToken(User user);

    /**
     * Recupera un objeto de autenticación basado en la solicitud HTTP.
     * Se utiliza para validar el token incluido en la solicitud y asociarlo con un usuario autenticado.
     *
     * @param request - La solicitud HTTP entrante que contiene el token JWT.
     * @return Un objeto Authentication que representa al usuario autenticado, o null si no es válido.
     */
    Authentication getAuthentication(HttpServletRequest request);

    /**
     * Valida si un token JWT presente en la solicitud HTTP es válido.
     * Este método verifica la integridad y validez del token, como la firma y la expiración.
     *
     * @param request - La solicitud HTTP que contiene el token JWT.
     * @return true si el token es válido, false en caso contrario.
     */
    boolean isTokenValid(HttpServletRequest request);
}
