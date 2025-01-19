package comapigateway.services_impl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import comapigateway.entities.Cooperacion;
import comapigateway.models.CrearCooperacionDto;
import comapigateway.repositories.CooperacionRepository;
import comapigateway.services.CooperacionService;

@Service
public class CooperacionServiceImpl implements CooperacionService {

	private static final Logger logger = LoggerFactory.getLogger(CooperacionServiceImpl.class);

	@Autowired
	private CooperacionRepository cooperacionRepository;
	

	@Transactional
	@Override
	public Cooperacion crearCooperacion(CrearCooperacionDto cDto) {

		logger.info("Iniciando creación de cooperación con datos: {}", cDto);
		
		
	
		try {
			// Mapeo del DTO a la entidad Cooperacion
			Cooperacion cooperacion = mapearDtoAEntidad(cDto);


			logger.info("Cooperación creada con éxito: {}", cooperacion);
		    return cooperacionRepository.save(cooperacion);
		} catch (DataAccessException ex) {
			logger.error("Error al acceder a la base de datos: {}", ex.getMessage());
			throw new RuntimeException("Error al guardar la cooperación en la base de datos", ex);
		} catch (Exception ex) {
			logger.error("Error inesperado: {}", ex.getMessage());
			throw new RuntimeException("Error inesperado al crear la cooperación", ex);
		}
	}

	private Cooperacion mapearDtoAEntidad(CrearCooperacionDto cDto) {
		Cooperacion cooperacion = new Cooperacion();
		cooperacion.setNombre(cDto.getNombre());
		cooperacion.setDescripcion(cDto.getDescripcion());
		cooperacion.setMontoObjetivo(cDto.getMontoObjetivo());
		cooperacion.setFechaInicio(LocalDate.parse(cDto.getFechaInicio().substring(0, 10)));
		cooperacion.setFechaFin(LocalDate.parse(cDto.getFechaFin().substring(0, 10)));
		cooperacion.setEstado("ACTIVO");
		cooperacion.setCreatedBy(cDto.getCreatedBy());
		cooperacion.setUpdatedBy(cDto.getUpdatedBy());
		
		cooperacion.setNoCuentaPago(cDto.getNumeroCuenta());
		String numeroCuenta = cDto.getNumeroCuenta();

		if(numeroCuenta != null) {
			cooperacion.setNoCuentaPago(numeroCuenta);
		} else {
			cooperacion.setNoCuentaPago("");
		}

		
		if (cDto.getMontoActual() == null) {
			cooperacion.setMontoActual(0.0);
		} else {
			cooperacion.setMontoActual(cDto.getMontoActual());
		}

		if (cDto.getMontoObjetivo() != null) {
		    try {
               
		    	double montoObjetivo = cDto.getMontoObjetivo();
		    	double montoActual = cDto.getMontoActual();
		    	
		    	double total = (montoObjetivo - montoActual);
		    	
		    	cooperacion.setMontoRestante(total);

		    } catch (NumberFormatException e) {
		        System.out.println("Error: El monto objetivo o monto actual no tiene un formato numérico válido.");
		    }
		} else {
		    System.out.println("Error: Monto objetivo o monto actual es nulo.");
		}

		
		if (cDto.getFechaCreacion() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
			cooperacion.setFechaCreacion(LocalDateTime
					.ofInstant(formatter.parse(cDto.getFechaCreacion(), java.time.Instant::from), ZoneOffset.UTC));
		}

		if (cDto.getUpdatedAt() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
			
			cooperacion.setUpdatedAt(LocalDateTime.ofInstant(formatter.parse(cDto.getFechaCreacion(), java.time.Instant::from), ZoneOffset.UTC));
		}
		
		cooperacion.setGrupo(cDto.getIdGrupo()); 
		cooperacion.setCategoria(cDto.getIdCategoria());
		return cooperacion;
	}

}
