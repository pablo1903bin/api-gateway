package comapigateway.models;

public class CooperacionDto {
	private Long id;
	private String nombre;
	private String descripcion;
	private String estado;
	private Double montoActual;
	private Double montoObjetivo;
	private Double montoRestante;

	public CooperacionDto() {

	}

	public CooperacionDto(Long id, String nombre, String descripcion, String estado, Double montoActual,
			Double montoObjetivo, Double montoRestante) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.montoActual = montoActual;
		this.montoObjetivo = montoObjetivo;
		this.montoRestante = montoRestante;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getMontoActual() {
		return montoActual;
	}

	public void setMontoActual(Double montoActual) {
		this.montoActual = montoActual;
	}

	public Double getMontoObjetivo() {
		return montoObjetivo;
	}

	public void setMontoObjetivo(Double montoObjetivo) {
		this.montoObjetivo = montoObjetivo;
	}

	public Double getMontoRestante() {
		return montoRestante;
	}

	public void setMontoRestante(Double montoRestante) {
		this.montoRestante = montoRestante;
	}

}
