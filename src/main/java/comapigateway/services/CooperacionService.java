package comapigateway.services;

import comapigateway.entities.Cooperacion;
import comapigateway.models.CrearCooperacionDto;

public interface CooperacionService {
	
	Cooperacion crearCooperacion(CrearCooperacionDto cooperacionDto);

}
