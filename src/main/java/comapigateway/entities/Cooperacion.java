package comapigateway.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tesora_cooperations", schema = "public")
public class Cooperacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String descripcion;

	@Column(name = "monto_objetivo", nullable = false, precision = 10, scale = 2)
	private Double montoObjetivo;

	@Column(name = "fecha_inicio", nullable = false)
	private LocalDate fechaInicio;

	@Column(name = "fecha_fin", nullable = false)
	private LocalDate fechaFin;

	@Column(name = "fecha_creacion", nullable = false, updatable = false)
	private LocalDateTime fechaCreacion;

	@Column(nullable = false)
	private String estado;

	@Column(name = "created_by", nullable = false)
	private Long createdBy;

	@Column(name = "update_by", nullable = false)
	private Long updatedBy;

	@Column(name = "monto_restante", precision = 10, scale = 2)
	private Double montoRestante;

	@Column(name = "monto_actual", precision = 10, scale = 2)
	private Double montoActual;

	@Column(name = "no_cuenta_pago")
	private String noCuentaPago;

	@Column(name = "group_id", nullable = false)
	private Integer grupo;

	@Column(name = "categoria_id", nullable = false)
	private Integer categoria;


	@Column(name = "update_at", nullable = false)
	private LocalDateTime updatedAt;



}
