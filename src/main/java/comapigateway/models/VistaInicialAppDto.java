package comapigateway.models;

public class VistaInicialAppDto {

	private Long idCaja;
	private Double saldoActual;
	private Double ingresosTotales;
	private Double egresosTotales;

	private Long cooperacionId;
	private String cooperacionNombre;
	private String cooperacionDescripcion;

	private String cooperacionEstado;

	private Double cooperacionMontoActual;
	private Double cooperacionMontoObjetivo;
	private Double cooperacionMontoRestante;

	public VistaInicialAppDto() {

	}

	public VistaInicialAppDto(Long idCaja, Double saldoActual, Double ingresosTotales, Double egresosTotales,
			Long cooperacionId, String cooperacionNombre, String cooperacionDescripcion, String cooperacionEstado,
			Double cooperacionMontoActual, Double cooperacionMontoObjetivo, Double cooperacionMontoRestante) {

		this.idCaja = idCaja;
		this.saldoActual = saldoActual;
		this.ingresosTotales = ingresosTotales;
		this.egresosTotales = egresosTotales;
		this.cooperacionId = cooperacionId;
		this.cooperacionNombre = cooperacionNombre;
		this.cooperacionDescripcion = cooperacionDescripcion;
		this.cooperacionEstado = cooperacionEstado;
		this.cooperacionMontoActual = cooperacionMontoActual;
		this.cooperacionMontoObjetivo = cooperacionMontoObjetivo;
		this.cooperacionMontoRestante = cooperacionMontoRestante;
	}

	public Long getId() {
		return idCaja;
	}

	public void setId(Long id) {
		this.idCaja = id;
	}

	public Double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Double getIngresosTotales() {
		return ingresosTotales;
	}

	public void setIngresosTotales(Double ingresosTotales) {
		this.ingresosTotales = ingresosTotales;
	}

	public Double getEgresosTotales() {
		return egresosTotales;
	}

	public void setEgresosTotales(Double egresosTotales) {
		this.egresosTotales = egresosTotales;
	}

	public Long getCooperacionId() {
		return cooperacionId;
	}

	public void setCooperacionId(Long cooperacionId) {
		this.cooperacionId = cooperacionId;
	}

	public String getCooperacionNombre() {
		return cooperacionNombre;
	}

	public void setCooperacionNombre(String cooperacionNombre) {
		this.cooperacionNombre = cooperacionNombre;
	}

	public String getCooperacionDescripcion() {
		return cooperacionDescripcion;
	}

	public void setCooperacionDescripcion(String cooperacionDescripcion) {
		this.cooperacionDescripcion = cooperacionDescripcion;
	}

	public String getCooperacionEstado() {
		return cooperacionEstado;
	}

	public void setCooperacionEstado(String cooperacionEstado) {
		this.cooperacionEstado = cooperacionEstado;
	}

	public Double getCooperacionMontoActual() {
		return cooperacionMontoActual;
	}

	public void setCooperacionMontoActual(Double cooperacionMontoActual) {
		this.cooperacionMontoActual = cooperacionMontoActual;
	}

	public Double getCooperacionMontoObjetivo() {
		return cooperacionMontoObjetivo;
	}

	public void setCooperacionMontoObjetivo(Double cooperacionMontoObjetivo) {
		this.cooperacionMontoObjetivo = cooperacionMontoObjetivo;
	}

	public Double getCooperacionMontoRestante() {
		return cooperacionMontoRestante;
	}

	public void setCooperacionMontoRestante(Double cooperacionMontoRestante) {
		this.cooperacionMontoRestante = cooperacionMontoRestante;
	}

	@Override
	public String toString() {
		return "VistaInicialAppDto [id=" + idCaja + ", saldoActual=" + saldoActual + ", ingresosTotales="
				+ ingresosTotales + ", egresosTotales=" + egresosTotales + ", cooperacionId=" + cooperacionId
				+ ", cooperacionNombre=" + cooperacionNombre + ", cooperacionDescripcion=" + cooperacionDescripcion
				+ ", cooperacionEstado=" + cooperacionEstado + ", cooperacionMontoActual=" + cooperacionMontoActual
				+ ", cooperacionMontoObjetivo=" + cooperacionMontoObjetivo + ", cooperacionMontoRestante="
				+ cooperacionMontoRestante + "]";
	}

}
