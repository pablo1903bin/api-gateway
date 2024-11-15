package comapigateway.models;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class RecordatorioDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
	private Long userId;
    private String nombreMedicamento;
    private String descripcion;
    private String dosis;
    private String metodoAdministracion;
    private Integer frecuenciaUnidades;
    private String frecuenciaIntervalo;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private String duracionTratamiento;
    private String estado;
    private LocalDate fechaCreacion;
    private Boolean activo;

	public RecordatorioDTO() {

	}

	public RecordatorioDTO(Long userId, String nombreMedicamento, String descripcion, String dosis,
			String metodoAdministracion, Integer frecuenciaUnidades, String frecuenciaIntervalo, LocalDate fechaInicio,
			LocalTime horaInicio, String duracionTratamiento, String estado, LocalDate fechaCreacion, Boolean activo) {

		this.userId = userId;
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
		this.activo = activo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "RecordatorioDTO [userId=" + userId + ", nombreMedicamento=" + nombreMedicamento + ", descripcion="
				+ descripcion + ", dosis=" + dosis + ", metodoAdministracion=" + metodoAdministracion
				+ ", frecuenciaUnidades=" + frecuenciaUnidades + ", frecuenciaIntervalo=" + frecuenciaIntervalo
				+ ", fechaInicio=" + fechaInicio + ", horaInicio=" + horaInicio + ", duracionTratamiento="
				+ duracionTratamiento + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", activo=" + activo
				+ "]";
	}
}