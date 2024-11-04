package comapigateway.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comapigateway.entities.Recordatorio;

public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {

	// Método para buscar recordatorios por el ID del usuario
	List<Recordatorio> findByUser(Long userId);

}
