package comapigateway.services_impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import comapigateway.entities.Recordatorio;
import comapigateway.models.RecordatorioDTO;
import comapigateway.repositories.RecordatorioRepository;
import comapigateway.services.RecordatorioServices;

@Service
public class RecordatorioServiceImpl implements RecordatorioServices {

    @Autowired
    private RecordatorioRepository recordatorioRepository;
    
    @Override
    public Recordatorio updateRecordatorio(Recordatorio recordatorio) {
    	
        if (recordatorio.getId() == null) {
            throw new IllegalArgumentException("El ID del recordatorio no puede ser nulo.");
        }
 
        Recordatorio existingRecordatorio = recordatorioRepository.findById(recordatorio.getId())
                .orElseThrow(() -> new RuntimeException("Recordatorio no encontrado con ID: " + recordatorio.getId()));

        // Actualiza los campos que no son nulos y valida las entradas
        if (recordatorio.getNombreMedicamento() != null && !recordatorio.getNombreMedicamento().isEmpty()) {
            existingRecordatorio.setNombreMedicamento(recordatorio.getNombreMedicamento());
        }
        if (recordatorio.getDescripcion() != null) {
            existingRecordatorio.setDescripcion(recordatorio.getDescripcion());
        }
        if (recordatorio.getDosis() != null) {
            existingRecordatorio.setDosis(recordatorio.getDosis());
        }
        if (recordatorio.getMetodoAdministracion() != null) {
            existingRecordatorio.setMetodoAdministracion(recordatorio.getMetodoAdministracion());
        }
        if (recordatorio.getFrecuenciaUnidades() != null && recordatorio.getFrecuenciaUnidades() > 0) {
            existingRecordatorio.setFrecuenciaUnidades(recordatorio.getFrecuenciaUnidades());
        }
        if (recordatorio.getFrecuenciaIntervalo() != null) {
            existingRecordatorio.setFrecuenciaIntervalo(recordatorio.getFrecuenciaIntervalo());
        }
        if (recordatorio.getFechaInicio() != null && !recordatorio.getFechaInicio().isBefore(LocalDate.now())) {
            existingRecordatorio.setFechaInicio(recordatorio.getFechaInicio());
        }
        if (recordatorio.getHoraInicio() != null) {
            existingRecordatorio.setHoraInicio(recordatorio.getHoraInicio());
        }
        if (recordatorio.getDuracionTratamiento() != null) {
            existingRecordatorio.setDuracionTratamiento(recordatorio.getDuracionTratamiento());
        }
        if (recordatorio.getEstado() != null) {
            existingRecordatorio.setEstado(recordatorio.getEstado());
        }

        // Guarda los cambios en la base de datos
        try {
            return recordatorioRepository.save(existingRecordatorio);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error al actualizar el recordatorio: conflicto con los datos existentes.", e);
        }
    }

    @Override
    public void deleteRecordatorio(Long idRecordatorio) {
        Optional<Recordatorio> existingRecordatorio = recordatorioRepository.findById(idRecordatorio);
        if (!existingRecordatorio.isPresent()) {
            throw new EntityNotFoundException("El recordatorio con ID " + idRecordatorio + " no existe.");
        }

        Recordatorio toDelete = existingRecordatorio.get();
        toDelete.setActiva(false); // Marcado como inactivo
        recordatorioRepository.save(toDelete); // Guardar los cambios
    }

    
    @Override
    public void deleteById(Integer id) {
        // Verificar si el registro existe antes de eliminarlo
        Optional<Recordatorio> recordatorio = recordatorioRepository.findById(id.longValue());
        if (recordatorio.isPresent()) {
            // Si existe, proceder con la eliminación
            recordatorioRepository.deleteById(id.longValue());
        } else {
            // Si no existe, lanzar una excepción personalizada
            throw new RuntimeException("No se encontró el recordatorio con ID: " + id);
        }
    }

	
	@Override
	public List<Recordatorio> findByIdUser(Long id) {
	 List<Recordatorio> list =	recordatorioRepository.findActiveRecordatoriosByUserIdNative(id);
		return list;
	}
	

    @Override
    public Recordatorio saveRecordatorio(RecordatorioDTO recordatorioDTO) {
        Recordatorio recordatorio = mapToEntity(recordatorioDTO);
        //System.out.println("Rcordatorio antes de guardar:   " + recordatorio.toString());
        Recordatorio savedRecordatorio = recordatorioRepository.save(recordatorio);
        return savedRecordatorio;
    }

    @Override
    public List<RecordatorioDTO> listRecordatorios() {
        List<Recordatorio> list = recordatorioRepository.findAll();
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RecordatorioDTO findById(Integer id) {
    	  //System.out.println("Buscar recordatorio con id: "+ id);
    	
    	  Optional<Recordatorio> recordatorioRepositori = recordatorioRepository.findRecordatorioById(id.longValue());
  
        if (recordatorioRepositori.isPresent()) {
        	System.out.println(recordatorioRepositori.get());
            return mapToDTO(recordatorioRepositori.get());
        } else {
            throw new RuntimeException("Recordatorio no encontrado con el ID: " + id);
        }
    }

    // Métodos auxiliares para mapear entre Recordatorio y RecordatorioDTO
    private Recordatorio mapToEntity(RecordatorioDTO dto) {
    	System.out.println(dto.toString());
        Recordatorio recordatorio = new Recordatorio();
        recordatorio.setDescripcion(dto.getDescripcion());
        recordatorio.setNombreMedicamento(dto.getNombreMedicamento());
        recordatorio.setDosis(dto.getDosis());
        recordatorio.setDuracionTratamiento(dto.getDuracionTratamiento());
        recordatorio.setEstado(dto.getEstado());
        recordatorio.setFechaInicio(dto.getFechaInicio());
        recordatorio.setFechaCreacion(dto.getFechaCreacion());
        recordatorio.setHoraInicio(dto.getHoraInicio());
        recordatorio.setMetodoAdministracion(dto.getMetodoAdministracion());
        recordatorio.setFrecuenciaIntervalo(dto.getFrecuenciaIntervalo());
        recordatorio.setFrecuenciaUnidades(dto.getFrecuenciaUnidades());
        recordatorio.setUser(dto.getUserId());
        recordatorio.setActiva(true);
        return recordatorio;
    }

    private RecordatorioDTO mapToDTO(Recordatorio recordatorio) {
    	
        RecordatorioDTO dto = new RecordatorioDTO();
        
        dto.setDescripcion(recordatorio.getDescripcion());
        dto.setNombreMedicamento(recordatorio.getNombreMedicamento());
        dto.setDosis(recordatorio.getDosis());
        dto.setDuracionTratamiento(recordatorio.getDuracionTratamiento());
        dto.setEstado(recordatorio.getEstado());
        dto.setFechaInicio(recordatorio.getFechaInicio());
        dto.setHoraInicio(recordatorio.getHoraInicio());
        dto.setMetodoAdministracion(recordatorio.getMetodoAdministracion());
        dto.setFrecuenciaIntervalo(recordatorio.getFrecuenciaIntervalo());
        dto.setFrecuenciaUnidades(recordatorio.getFrecuenciaUnidades());
        dto.setUserId(recordatorio.getUser());
        return dto;
    }

}
