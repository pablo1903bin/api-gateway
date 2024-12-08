package comapigateway.services;

import java.util.List;

import comapigateway.entities.Recordatorio;
import comapigateway.models.RecordatorioDTO;



public interface RecordatorioServices {
	
	Recordatorio saveRecordatorio(RecordatorioDTO recordatorio);

	List<RecordatorioDTO> listRecordatorios();
    
	RecordatorioDTO findById(Integer id);
	
	List<Recordatorio> findByIdUser(Long id);
	
	 void deleteById(Integer id);
	 
	// Método para actualizar usando la entidad Recordatorio
	Recordatorio updateRecordatorio(Recordatorio recordatorio);
	
	void deleteRecordatorio(Long idRecordatorio);
}
