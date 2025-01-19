package comapigateway.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import comapigateway.entities.Group;

import java.util.List;

@Repository
public interface CajaCooperacionRepository extends org.springframework.data.repository.Repository<Group, Long> {

    @Query(value = "SELECT " +
            "c.id AS id, " +
            "c.saldo_actual AS saldoActual, " +
            "c.ingresos_totales AS ingresosTotales, " +
            "c.egresos_totales AS egresosTotales, " +
            "tc.id AS cooperacionId, " +
            "tc.nombre AS cooperacionNombre, " +
            "tc.descripcion AS cooperacionDescripcion, " +
            "tc.estado AS cooperacionEstado, " +
            "tc.monto_actual AS cooperacionMontoActual, " +
            "tc.monto_objetivo AS cooperacionMontoObjetivo, " +
            "tc.monto_restante AS cooperacionMontoRestante " +
            "FROM caja c " +
            "JOIN tesora_cooperations tc ON c.group_id = tc.group_id " +
            "WHERE tc.group_id = :groupId " +
            "AND (tc.created_by = :userId OR :userId IN ( " +
            "    SELECT user_id " +
            "    FROM user_groups " +
            "    WHERE group_id = tc.group_id " +
            "))",
            nativeQuery = true)
    List<Object[]> findDatosInicialesByUserAndGroup(
    	    @Param("userId") Long userId,
    	    @Param("groupId") Long groupId
    	);
}
