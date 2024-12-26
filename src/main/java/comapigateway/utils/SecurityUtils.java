package comapigateway.utils;

// Importa clases necesarias para manejar roles y solicitudes HTTP.
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Representa una autoridad o rol en Spring Security.
import org.springframework.util.StringUtils; // Utilidad para manejar cadenas de texto.
import javax.servlet.http.HttpServletRequest; // Permite acceder a detalles de la solicitud HTTP.

/*
 * Clase de utilidades para manejar la seguridad en la aplicación.
 * Contiene métodos estáticos relacionados con la transformación de roles y extracción de tokens.
 */
public class SecurityUtils {

    // Prefijo estándar para roles en Spring Security.
    public static final String ROLE_PREFIX = "ROLE_";

    // Nombre de la cabecera HTTP donde se espera el token de autenticación.
    public static final String AUTH_HEADER = "authorization";

    // Tipo de token esperado (Bearer Token).
    public static final String AUTH_TOKEN_TYPE = "Bearer";

    // Prefijo que se utiliza antes del token real en la cabecera.
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";

    /**
     * Convierte un rol almacenado en la base de datos al formato requerido por Spring Security.
     * Si el rol no comienza con "ROLE_", se agrega automáticamente.
     *
     * @param role El rol almacenado en la base de datos.
     * @return Un objeto SimpleGrantedAuthority con el formato adecuado.
     */
    public static SimpleGrantedAuthority convertToAuthority(String role) {
        // Si el rol ya comienza con "ROLE_", se mantiene. Si no, se agrega el prefijo.
        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(formattedRole); // Devuelve la autoridad formateada.
    }

    /**
     * Extrae el token JWT de la cabecera `Authorization` de una solicitud HTTP.
     *
     * @param request La solicitud HTTP entrante.
     * @return El token JWT si está presente y es válido, o null si no lo está.
     */
    public static String extractAuthTokenFromRequest(HttpServletRequest request) {
        // Obtiene el valor de la cabecera "authorization".
        String bearerToken = request.getHeader(AUTH_HEADER);

        // Verifica si la cabecera contiene un token válido (no vacío y comienza con "Bearer ").
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
            // Elimina el prefijo "Bearer " y devuelve el token real.
            return bearerToken.substring(AUTH_TOKEN_PREFIX.length());
        }

        // Si no se encuentra un token válido, devuelve null.
        return null;
    }

}
