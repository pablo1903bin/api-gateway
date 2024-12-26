package comapigateway.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class CrearCooperacionDto {

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;

	@NotNull(message = "El monto objetivo no puede ser nulo")
	private Double montoObjetivo;

	private String numeroCuenta;

	@NotNull(message = "La fecha inicial es obligatoria")
	@Pattern(
	    regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?(Z|[+-]\\d{2}:\\d{2})?)?$",
	    message = "La fecha debe tener el formato yyyy-MM-dd o yyyy-MM-ddTHH:mm:ssZ"
	)
	private String fechaInicio;

	@NotNull(message = "La fecha final es obligatoria")
	@Pattern(
	    regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?(Z|[+-]\\d{2}:\\d{2})?)?$",
	    message = "La fecha debe tener el formato yyyy-MM-dd o yyyy-MM-ddTHH:mm:ssZ"
	)
	private String fechaFin;

	private String estado;

	@NotNull(message = "El ID del creador es obligatorio")
	private Long createdBy;

	@NotNull(message = "El Monto Actual es obligatorio")
	private Double montoActual;

	private List<Integer> participantes;

	@NotNull(message = "El Id del actualizador es obligatorio")
	private Long updatedBy;

	@NotNull(message = "La fecha de actualizacion es obligatoria")
	@Pattern(
	    regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?(Z|[+-]\\d{2}:\\d{2})?)?$",
	    message = "La fecha de actualización debe estar en formato ISO 8601 o yyyy-MM-dd"
	)
	private String updatedAt;

	private Integer tipoCooperacion;
	
	@NotNull(message = "La fecha de creacion es obligatoria")
	@Pattern(
	    regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?(Z|[+-]\\d{2}:\\d{2})?)?$",
	    message = "La fecha de creación debe estar en formato ISO 8601 o yyyy-MM-dd"
	)
	private String fechaCreacion;

	@NotNull(message = "El Id de Grupo es obligartorio")
	private Integer idGrupo;

	@NotNull(message = "El ID de la categoria es obligatorio")
	private Integer idCategoria;

	// Constructor vacío
	public CrearCooperacionDto() {
	}

	// Constructor con todos los campos montoActual
	public CrearCooperacionDto(@JsonProperty("nombre") String nombre, @JsonProperty("descripcion") String descripcion,
			@JsonProperty("montoObjetivo") Double montoObjetivo, @JsonProperty("montoActual") Double montoActual,
			@JsonProperty("numeroCuenta") String numeroCuenta, @JsonProperty("fechaInicio") String fechaInicio,
			@JsonProperty("fechaFin") String fechaFin, @JsonProperty("estado") String estado,
			@JsonProperty("createdBy") Long createdBy, @JsonProperty("metaRecaudada") Double metaRecaudada,
			@JsonProperty("participantes") List<Integer> participantes, @JsonProperty("updatedBy") Long updatedBy,
			@JsonProperty("updatedAt") String updatedAt, @JsonProperty("tipoCooperacion") Integer tipoCooperacion,
			@JsonProperty("fechaCreacion") String fechaCreacion, @JsonProperty("idGrupo") Integer idGrupo,
			@JsonProperty("idCategoria") Integer idCategoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.montoObjetivo = montoObjetivo;
		this.numeroCuenta = numeroCuenta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.createdBy = createdBy;
		this.montoActual = montoActual;
		this.participantes = participantes;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
		this.tipoCooperacion = tipoCooperacion;
		this.fechaCreacion = fechaCreacion;
		this.idGrupo = idGrupo;
		this.idCategoria = idCategoria;
	}

	// Getters y Setters
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

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getMontoActual() {
		return montoActual;
	}

	public void setMontoActual(Double metaRecaudada) {
		this.montoActual = metaRecaudada;
	}

	public List<Integer> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Integer> participantes) {
		this.participantes = participantes;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getTipoCooperacion() {
		return tipoCooperacion;
	}

	public void setTipoCooperacion(Integer tipoCooperacion) {
		this.tipoCooperacion = tipoCooperacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "CrearCooperacionDto [nombre=" + nombre + ", descripcion=" + descripcion + ", montoObjetivo="
				+ montoObjetivo + ", numeroCuenta=" + numeroCuenta + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", estado=" + estado + ", createdBy=" + createdBy + ", montoActual=" + montoActual
				+ ", participantes=" + participantes + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt
				+ ", tipoCooperacion=" + tipoCooperacion + ", fechaCreacion=" + fechaCreacion + ", IdGrupo=" + idGrupo
				+ ", idCategoria=" + idCategoria + "]";
	}

}
