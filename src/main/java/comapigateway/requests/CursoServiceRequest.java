package comapigateway.requests;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Este es el mapa que me lleva asia el controller principal de este recurso
//Define una interfaz para interactuar con un servicio externo usando Feign Client
@FeignClient(
		value = "cursos-service", // El nombre del servicio al que se le hará las solicitudes
		path = "/cursos/curso", // El prefijo del path que se usará en todas las solicitudes de esta interfaz
	    url="${curso.service.url}", // Comentado: Aquí se podría especificar la URL del servicio en las propiedades
		configuration = FeignConfiguration.class // Configuración personalizada para Feign (si es necesaria)
)
public interface CursoServiceRequest {

	// Mapea este método a una solicitud HTTP POST a "/api/inmueble"
	@PostMapping
	Object saveCursos(@RequestBody Object requestBody); // Método para guardar un inmueble, tomando el cuerpo de la
															// solicitud como parámetro
	// Mapea este método a una solicitud HTTP DELETE a "/api/inmueble/{inmuebleId}"
	@DeleteMapping("{cursoId}")
	void deleteCursos(@PathVariable("cursoId") Long cursoId); // Método para eliminar un inmueble por su ID

	// Mapea este método a una solicitud HTTP GET a "/api/inmueble"
	@GetMapping("all")
	List<Object> getAllCursos(); // Método para obtener todos los inmuebles
}