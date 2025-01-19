package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comapigateway.models.ApiResponse;
import comapigateway.models.CajaDto;
import comapigateway.services.DataInicialService;

@RestController
@RequestMapping("/api/data")
public class DataInicialController {

    @Autowired
    private DataInicialService dataInicialService;

    @GetMapping("/inicial")
    public ResponseEntity<ApiResponse<CajaDto>> obtenerDatosIniciales(
            @RequestParam("userId") Long userId,
            @RequestParam("groupId") Long groupId) {

        CajaDto datos = dataInicialService.obtenerDatosIniciales(userId, groupId);

        // Construir la respuesta genérica
        ApiResponse<CajaDto> response = new ApiResponse<>(
            "200",
            datos.getCooperaciones().isEmpty() ? "No se encontraron cooperaciones." : "Datos iniciales obtenidos con éxito.",
            datos
        );

        // Retornar la respuesta
        return ResponseEntity.ok(response);
    }
}
