package comapigateway.services;

import java.util.List;

import comapigateway.models.VistaInicialAppDto;

public interface DataInicialService {

	List<VistaInicialAppDto> obtenerDatosIniciales(Long userId, Long groupId);
	
}
