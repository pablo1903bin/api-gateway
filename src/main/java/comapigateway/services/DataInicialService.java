package comapigateway.services;

import comapigateway.models.CajaDto;

public interface DataInicialService {

	CajaDto obtenerDatosIniciales(Long userId, Long groupId);
	
}
