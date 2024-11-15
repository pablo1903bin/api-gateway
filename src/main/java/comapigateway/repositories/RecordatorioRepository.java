package comapigateway.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import comapigateway.entities.Recordatorio;

public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {

	@Query("SELECT r FROM Recordatorio r WHERE r.id = :id")
	Optional<Recordatorio> findRecordatorioById(@Param("id") Long id);

	@Query(value = "SELECT * FROM recordatorios WHERE id = :id", nativeQuery = true)
	Optional<Recordatorio> findRecordatorioByIdNative(@Param("id") Long id);

	@Query(value = "SELECT * FROM recordatorios WHERE user_id = :userId AND activo = true", nativeQuery = true)
	List<Recordatorio> findActiveRecordatoriosByUserIdNative(@Param("userId") Long userId);

}
