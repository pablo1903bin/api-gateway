package comapigateway.services_impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comapigateway.models.VistaInicialAppDto;
import comapigateway.repositories.CajaCooperacionRepository;
import comapigateway.services.DataInicialService;

@Service
public class DataInicialServiceImpl implements DataInicialService {
	
	
	@Autowired
	private CajaCooperacionRepository vistaInicialAppRepository;

	@Override
	@Transactional
	public List<VistaInicialAppDto> obtenerDatosIniciales(Long userId, Long groupId) {
	    List<Object[]> results = vistaInicialAppRepository.findDatosInicialesByUserAndGroup(userId, groupId);
	    return results.stream()
	    	    .map(row -> new VistaInicialAppDto(
	    	        (Long) row[0], (Double) row[1], (Double) row[2], (Double) row[3],
	    	        (Long) row[4], (String) row[5], (String) row[6], 
	    	        (String) row[7], (Double) row[8], (Double) row[9], (Double) row[10]
	    	    ))
	    	    .collect(Collectors.toList());

	}


}
