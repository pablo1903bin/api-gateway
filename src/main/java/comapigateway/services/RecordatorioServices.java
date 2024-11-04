package comapigateway.services;

import java.util.List;

import comapigateway.entities.Recordatorio;
import comapigateway.models.RecordatorioDTO;



public interface RecordatorioServices {
	
	RecordatorioDTO saveRecordatorio(RecordatorioDTO recordatorio);

	List<RecordatorioDTO> listRecordatorios();
    
	RecordatorioDTO findById(Integer id);
	
	List<Recordatorio> findByIdUser(Long id);
	
}
