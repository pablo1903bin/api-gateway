package comapigateway.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

// La clase extiende OncePerRequestFilter, asegurando que este filtro se ejecute solo una vez por cada solicitud HTTP.
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    // Inyección del proveedor de JWT (Json Web Token), que contiene lógica para validar tokens y obtener autenticación.
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Este método se ejecuta para cada solicitud entrante.
     * Su objetivo es interceptar la solicitud, validar el JWT si está presente,
     * y establecer la autenticación en el contexto de seguridad.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Obtiene la autenticación basada en el JWT de la solicitud.
        Authentication authentication = jwtProvider.getAuthentication(request);
        
        // Si la autenticación no es nula y el token es válido, establece la autenticación en el contexto de seguridad.
        if (authentication != null && jwtProvider.isTokenValid(request)) {
            // Coloca la autenticación en el SecurityContextHolder, permitiendo que las partes posteriores del código accedan al usuario autenticado.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continúa con el siguiente filtro en la cadena de filtros.
        filterChain.doFilter(request, response);
    }
}
