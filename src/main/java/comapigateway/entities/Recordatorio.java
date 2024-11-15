package comapigateway.entities;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "recordatorios")
public class Recordatorio
implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long user;

	@Column(name = "nombre_medicamento", nullable = false)
	private String nombreMedicamento;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "dosis")
	private String dosis;

	@Column(name = "metodo_administracion")
	private String metodoAdministracion;

	@Column(name = "frecuencia_unidades")
	private Integer frecuenciaUnidades;

	@Column(name = "frecuencia_intervalo")
	private String frecuenciaIntervalo;

	@Column(name = "fecha_inicio")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;

	@Column(name = "hora_inicio")
	private LocalTime horaInicio;

	@Column(name = "duracion_tratamiento")
	private String duracionTratamiento;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "fecha_creacion", nullable = false)
	private LocalDate fechaCreacion;

    @Column(name = "activo", nullable = false)
    private Boolean activa = true; // Nuevo campo con valor predeterminado
    
	// Constructor por defecto
	public Recordatorio() {
	}

	public Recordatorio(Long id, Long user, String nombreMedicamento, String descripcion, String dosis,
			String metodoAdministracion, Integer frecuenciaUnidades, String frecuenciaIntervalo, LocalDate fechaInicio,
			LocalTime horaInicio, String duracionTratamiento, String estado, LocalDate fechaCreacion, Boolean activa) {
		this.id = id;
		this.user = user;
		this.nombreMedicamento = nombreMedicamento;
		this.descripcion = descripcion;
		this.dosis = dosis;
		this.metodoAdministracion = metodoAdministracion;
		this.frecuenciaUnidades = frecuenciaUnidades;
		this.frecuenciaIntervalo = frecuenciaIntervalo;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.duracionTratamiento = duracionTratamiento;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.activa = activa;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getNombreMedicamento() {
		return nombreMedicamento;
	}

	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = nombreMedicamento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getMetodoAdministracion() {
		return metodoAdministracion;
	}

	public void setMetodoAdministracion(String metodoAdministracion) {
		this.metodoAdministracion = metodoAdministracion;
	}

	public Integer getFrecuenciaUnidades() {
		return frecuenciaUnidades;
	}

	public void setFrecuenciaUnidades(Integer frecuenciaUnidades) {
		this.frecuenciaUnidades = frecuenciaUnidades;
	}

	public String getFrecuenciaIntervalo() {
		return frecuenciaIntervalo;
	}

	public void setFrecuenciaIntervalo(String frecuenciaIntervalo) {
		this.frecuenciaIntervalo = frecuenciaIntervalo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDuracionTratamiento() {
		return duracionTratamiento;
	}

	public void setDuracionTratamiento(String duracionTratamiento) {
		this.duracionTratamiento = duracionTratamiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Recordatorio [id=" + id + ", user=" + user + ", nombreMedicamento=" + nombreMedicamento
				+ ", descripcion=" + descripcion + ", dosis=" + dosis + ", metodoAdministracion=" + metodoAdministracion
				+ ", frecuenciaUnidades=" + frecuenciaUnidades + ", frecuenciaIntervalo=" + frecuenciaIntervalo
				+ ", fechaInicio=" + fechaInicio + ", horaInicio=" + horaInicio + ", duracionTratamiento="
				+ duracionTratamiento + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", activa=" + activa
				+ "]";
	}

}
