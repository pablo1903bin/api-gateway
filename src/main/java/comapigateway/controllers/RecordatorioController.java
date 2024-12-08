package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comapigateway.entities.Recordatorio;
import comapigateway.models.ApiResponse;
import comapigateway.models.RecordatorioDTO;
import comapigateway.services.RecordatorioServices;

import java.util.List;


@RestController
@RequestMapping("api/recordatorio")
public class RecordatorioController {

	@Autowired
	private RecordatorioServices recordatorioServices;

	// Crear un nuevo recordatorio
	@PostMapping("/crear")
	public ResponseEntity<ApiResponse<Recordatorio>> createRecordatorio(@RequestBody RecordatorioDTO recordatorioDTO) {
		
		Recordatorio savedRecordatorio = recordatorioServices.saveRecordatorio(recordatorioDTO);

		ApiResponse<Recordatorio> respuesta = new ApiResponse<>("OK", "Recordatorio creado con éxito",
				savedRecordatorio);

		return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
	}

	// Actualizar un recordatorio existente
	@PatchMapping("/actualizar")
	public ResponseEntity<ApiResponse<Recordatorio>> updateRecordatorio(@RequestBody Recordatorio recordatorio) {
		Recordatorio updatedRecordatorio = recordatorioServices.updateRecordatorio(recordatorio);

		ApiResponse<Recordatorio> respuesta = new ApiResponse<>("OK", "Recordatorio actualizado con éxito",
				updatedRecordatorio);

		return ResponseEntity.ok(respuesta);
	}

	// Obtener todos los recordatorios
	@GetMapping("/todos")
	public ResponseEntity<List<RecordatorioDTO>> getAllRecordatorios() {
		List<RecordatorioDTO> recordatorios = recordatorioServices.listRecordatorios();
		return ResponseEntity.ok(recordatorios);
	}

	// Obtener un recordatorio por ID de usuario
	@GetMapping("/usuario/{id}")
	public ResponseEntity<ApiResponse<List<Recordatorio>>> getRecordatorioByIdUser(@PathVariable Long id) {
		List<Recordatorio> recordatorios = recordatorioServices.findByIdUser(id);

		ApiResponse<List<Recordatorio>> respuesta = new ApiResponse<>("OK",
				recordatorios.isEmpty() ? "No se encontraron recordatorios para el usuario con ID: " + id
						: "Lista de recordatorios obtenida con éxito",
				recordatorios);

		return ResponseEntity.ok(respuesta);
	}

	// Obtener un recordatorio por ID
	@GetMapping("/{id}")
	public ResponseEntity<RecordatorioDTO> getRecordatorioById(@PathVariable Integer id) {
		RecordatorioDTO recordatorioDTO = recordatorioServices.findById(id);
		return ResponseEntity.ok(recordatorioDTO);
	}

	// Eliminar un recordatorio por ID
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<ApiResponse<String>> deleteRecordatorio(@PathVariable Long id) {
		recordatorioServices.deleteRecordatorio(id);

		ApiResponse<String> respuesta = new ApiResponse<>("OK", "Recordatorio eliminado con éxito",
				"Recordatorio con ID " + id + " ha sido eliminado");

		return ResponseEntity.ok(respuesta);
	}

}
