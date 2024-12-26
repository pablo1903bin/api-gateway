package comapigateway.requests;

// Importa las clases necesarias para la configuración y seguridad.
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * Configuración de Feign para manejar la autenticación básica entre microservicios.
 */
@Configuration // Marca esta clase como una configuración para Spring.
public class FeignConfiguration {

    /**
     * Define un interceptor de autenticación básica para Feign.
     * Esto asegura que todas las solicitudes realizadas por Feign incluyan un encabezado
     * de autenticación básica con las credenciales especificadas.
     *
     * @param username Nombre de usuario cargado desde las propiedades.
     * @param password Contraseña cargada desde las propiedades.
     * @return Un objeto BasicAuthRequestInterceptor que añade las credenciales a las solicitudes.
     */
    @Bean
    BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${service.security.secure-key-username}") String username, // Obtiene el nombre de usuario desde el archivo de configuración.
            @Value("${service.security.secure-key-password}") String password) { // Obtiene la contraseña desde el archivo de configuración.
        return new BasicAuthRequestInterceptor(username, password); // Crea el interceptor con las credenciales proporcionadas.
    }

}
