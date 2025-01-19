package comapigateway.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comapigateway.entities.Cooperacion;
import comapigateway.models.ApiResponse;
import comapigateway.models.CrearCooperacionDto;
import comapigateway.services.CooperacionService;

@RestController
@RequestMapping("/api/cooperacion")
public class CooperacionController {
	
	private static final Logger logger = LoggerFactory.getLogger(CooperacionController.class);
	
	@Autowired
	CooperacionService cooperacionService;
	
	@PostMapping("/crear")
	public ResponseEntity<ApiResponse<Cooperacion>> crearCooperacion(@Valid @RequestBody CrearCooperacionDto cooperacionDto) {
		  logger.info("Solicitud para crear cooperación: {}", cooperacionDto);

		    Cooperacion cooperacion = cooperacionService.crearCooperacion(cooperacionDto);
	    
        ApiResponse<Cooperacion> response = new ApiResponse<>("201", "Cooperación creada exitosamente", cooperacion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
