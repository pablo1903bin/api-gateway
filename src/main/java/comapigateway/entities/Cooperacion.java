package comapigateway.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	@Column(name = "grupo_id", nullable = false)
	private Integer grupo;

	@Column(name = "categoria_id", nullable = false)
	private Integer categoria;


	@Column(name = "update_at", nullable = false)
	private LocalDateTime updatedAy;

	public Cooperacion() {
	}

	public Cooperacion(Long id, String nombre, String descripcion, Double montoObjetivo, LocalDate fechaInicio,
			LocalDate fechaFin, LocalDateTime fechaCreacion, String estado, Long createdBy, Long updatedBy,
			Double montoRestante, Double montoActual, String noCuentaPago, Integer grupo, Integer categoria,
		 LocalDateTime updatedAy) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.montoObjetivo = montoObjetivo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.montoRestante = montoRestante;
		this.montoActual = montoActual;
		this.noCuentaPago = noCuentaPago;
		this.grupo = grupo;
		this.categoria = categoria;
		this.updatedAy = updatedAy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAy;
	}

	public void setUpdatedAt(LocalDateTime updatedAy) {
		this.updatedAy = updatedAy;
	}

	public Double getMontoActual() {
		return montoActual;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setMontoActual(Double montoActual) {
		this.montoActual = montoActual;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMontoObjetivo() {
		return montoObjetivo;
	}

	public void setMontoObjetivo(Double montoObjetivo) {
		this.montoObjetivo = montoObjetivo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Double getMontoRestante() {
		return montoRestante;
	}

	public void setMontoRestante(Double montoRestante) {
		this.montoRestante = montoRestante;
	}

	public String getNoCuentaPago() {
		return noCuentaPago;
	}

	public void setNoCuentaPago(String noCuentaPago) {
		this.noCuentaPago = noCuentaPago;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

}
