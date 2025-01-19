package comapigateway.services_impl;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comapigateway.models.CajaDto;
import comapigateway.models.CooperacionDto;
import comapigateway.models.VistaInicialAppDto;
import comapigateway.repositories.CajaCooperacionRepository;
import comapigateway.services.DataInicialService;

@Service
public class DataInicialServiceImpl implements DataInicialService {
	
	
	@Autowired
	private CajaCooperacionRepository vistaInicialAppRepository;

	@Override
	@Transactional
	public CajaDto obtenerDatosIniciales(Long userId, Long groupId) {
	    List<Object[]> results = vistaInicialAppRepository.findDatosInicialesByUserAndGroup(userId, groupId);

	    // Verificar si no hay resultados
	    if (results.isEmpty()) {
	        // Retornar un objeto vacío con valores predeterminados
	        CajaDto cajaDto = new CajaDto();
	        cajaDto.setId(null);
	        cajaDto.setSaldoActual(0.0);
	        cajaDto.setIngresosTotales(0.0);
	        cajaDto.setEgresosTotales(0.0);
	        cajaDto.setCooperaciones(Collections.emptyList()); // Lista vacía de cooperaciones
	        return cajaDto;
	    }

	    // Obtener la información de la caja
	    Object[] firstRow = results.get(0);

	    // Construir el DTO de la caja
	    CajaDto cajaDto = new CajaDto();
	    cajaDto.setId(((BigInteger) firstRow[0]).longValue());
	    cajaDto.setSaldoActual((Double) firstRow[1]);
	    cajaDto.setIngresosTotales((Double) firstRow[2]);
	    cajaDto.setEgresosTotales((Double) firstRow[3]);

	    // Agregar las cooperaciones relacionadas
	    List<CooperacionDto> cooperaciones = results.stream()
	        .map(row -> new CooperacionDto(
	            ((BigInteger) row[4]).longValue(),
	            (String) row[5], 
	            (String) row[6], 
	            (String) row[7], 
	            (Double) row[8], 
	            (Double) row[9], 
	            (Double) row[10]
	        ))
	        .collect(Collectors.toList());

	    cajaDto.setCooperaciones(cooperaciones);

	    return cajaDto;
	}



}
