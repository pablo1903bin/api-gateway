package comapigateway.services_impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Recordatorio> findByIdUser(Long id) {
	 List<Recordatorio> list =	recordatorioRepository.findByUser(id);
		return list;
	}

    @Override
    public RecordatorioDTO saveRecordatorio(RecordatorioDTO recordatorioDTO) {
        Recordatorio recordatorio = mapToEntity(recordatorioDTO);
        System.out.println("Rcordatorio antes de guardar:   " + recordatorio.toString());
        Recordatorio savedRecordatorio = recordatorioRepository.save(recordatorio);
        return mapToDTO(savedRecordatorio);
    }

    @Override
    public List<RecordatorioDTO> listRecordatorios() {
        List<Recordatorio> list = recordatorioRepository.findAll();
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RecordatorioDTO findById(Integer id) {
        Optional<Recordatorio> recordatorioOpt = recordatorioRepository.findById(id.longValue());
        if (recordatorioOpt.isPresent()) {
            return mapToDTO(recordatorioOpt.get());
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
