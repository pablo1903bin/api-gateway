package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comapigateway.entities.Recordatorio;
import comapigateway.models.ApiResponse;
import comapigateway.models.RecordatorioDTO;
import comapigateway.services.RecordatorioServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/recordatorio")
public class RecordatorioController {

	@Autowired
	private RecordatorioServices recordatorioServices;

	// Crear un nuevo recordatorio
	@PostMapping("/crear")
	public ResponseEntity<ApiResponse<RecordatorioDTO>> createRecordatorio(@RequestBody RecordatorioDTO recordatorioDTO) {
	    try {
	        // Guardar el recordatorio
	        RecordatorioDTO savedRecordatorio = recordatorioServices.saveRecordatorio(recordatorioDTO);

	        // Crear la respuesta con el objeto guardado y un código 201
	        ApiResponse<RecordatorioDTO> respuesta = new ApiResponse<>(
	            "OK",
	            "Recordatorio creado con éxito",
	            savedRecordatorio
	        );

	        // Retornar la respuesta con el código HTTP 201 Created
	        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        // En caso de error, retornar una respuesta con código 500
	        ApiResponse<RecordatorioDTO> respuestaError = new ApiResponse<>(
	           "ERROR",
	            "Ocurrió un error al crear el recordatorio: " + e.getMessage(),
	            null
	        );
	        return new ResponseEntity<>(respuestaError, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	// Obtener todos los recordatorios
	@GetMapping("/todos")
	public ResponseEntity<?> getAllRecordatorios() {
		return new ResponseEntity<>(recordatorioServices.listRecordatorios(), HttpStatus.OK);
	}

	// Obtener un recordatorio por ID de usuario
	@GetMapping("/usuario/{id}")
	public ResponseEntity<ApiResponse<List<Recordatorio>>> getRecordatorioByIdUser(@PathVariable Long id) {
		try {
			List<Recordatorio> list = recordatorioServices.findByIdUser(id);

			// Si la lista está vacía, retornar un código 404 indicando que no se
			// encontraron recordatorios
			if (list.isEmpty()) {
				ApiResponse<List<Recordatorio>> respuestaNotFound = new ApiResponse<>("OK",
						"No se encontraron recordatorios para el usuario con ID: " + id, new ArrayList<>());
				return new ResponseEntity<>(respuestaNotFound, HttpStatus.NOT_FOUND);
			}

			// Si se encontraron recordatorios, devolverlos con un código 200
			ApiResponse<List<Recordatorio>> respuesta = new ApiResponse<>("OK",
					"Lista de recordatorios obtenida con éxito", list);

			return ResponseEntity.ok(respuesta);
		} catch (RuntimeException e) {
			// En caso de error, retornar una respuesta con código 500
			ApiResponse<List<Recordatorio>> respuestaError = new ApiResponse<>("ERROR",
					"Ocurrió un error al obtener los recordatorios: " + e.getMessage(), new ArrayList<>());
			return new ResponseEntity<>(respuestaError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Obtener un recordatorio por ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getRecordatorioById(@PathVariable Integer id) {
		try {
			RecordatorioDTO recordatorioDTO = recordatorioServices.findById(id);
			return new ResponseEntity<>(recordatorioDTO, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Eliminar un recordatorio
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRecordatorio(@PathVariable Integer id) {
		try {
			Optional<RecordatorioDTO> recordatorio = Optional.ofNullable(recordatorioServices.findById(id));
			if (recordatorio.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>("Recordatorio no encontrado.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
