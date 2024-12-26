package comapigateway.requests;

// Importa las clases necesarias para manejar solicitudes HTTP y Feign Client.
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interfaz Feign para interactuar con el servicio de cursos.
 * Define las operaciones que el cliente puede realizar en el servicio de cursos.
 */
@FeignClient(
        value = "cursos-service", // Nombre del servicio que representa este cliente Feign.
        path = "/cursos", // Prefijo que se aplicará a todas las rutas de esta interfaz.
        url = "${curso.service.url}", // URL del servicio, definida en el archivo de propiedades.
        configuration = FeignConfiguration.class // Configuración personalizada para este cliente Feign.
)
public interface CursoServiceRequest {

    /**
     * Guarda un curso en el servicio de cursos.
     * Envía una solicitud HTTP POST al endpoint "/cursos" del servicio externo.
     *
     * @param requestBody El cuerpo de la solicitud que contiene los datos del curso.
     * @return Un objeto que representa la respuesta del servicio.
     */
    @PostMapping
    Object saveCursos(@RequestBody Object requestBody);

    /**
     * Elimina un curso en el servicio de cursos por su ID.
     * Envía una solicitud HTTP DELETE al endpoint "/cursos/{cursoId}" del servicio externo.
     *
     * @param cursoId El ID del curso que se desea eliminar.
     */
    @DeleteMapping("{cursoId}")
    void deleteCursos(@PathVariable("cursoId") Long cursoId);

    /**
     * Obtiene todos los cursos desde el servicio de cursos.
     * Envía una solicitud HTTP GET al endpoint "/cursos/all" del servicio externo.
     *
     * @return Una cadena (String) que representa la lista de cursos obtenida.
     */
    @GetMapping("all")
    String getAllCursos();
}
