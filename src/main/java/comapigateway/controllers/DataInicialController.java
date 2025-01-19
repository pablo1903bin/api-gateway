package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comapigateway.models.VistaInicialAppDto;
import comapigateway.services.DataInicialService;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataInicialController {

    @Autowired
    private DataInicialService dataInicialService;

	@GetMapping("/inicial")
    public ResponseEntity<List<VistaInicialAppDto>> obtenerDatosIniciales(
            @RequestParam("userId") Long userId,
            @RequestParam("groupId") Long groupId) {
        // Llama al servicio para obtener los datos iniciales
        List<VistaInicialAppDto> datos = dataInicialService.obtenerDatosIniciales(userId, groupId);

        // Devuelve los datos en la respuesta
        return ResponseEntity.ok(datos);
    }
}
