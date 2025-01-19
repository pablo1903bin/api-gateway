package comapigateway.models;

import java.util.List;

public class CajaDto {
	private Long id;
	private Double saldoActual;
	private Double ingresosTotales;
	private Double egresosTotales;
	private List<CooperacionDto> cooperaciones;

	public CajaDto() {

	}

	public CajaDto(Long id, Double saldoActual, Double ingresosTotales, Double egresosTotales,
			List<CooperacionDto> cooperaciones) {

		this.id = id;
		this.saldoActual = saldoActual;
		this.ingresosTotales = ingresosTotales;
		this.egresosTotales = egresosTotales;
		this.cooperaciones = cooperaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<CooperacionDto> getCooperaciones() {
		return cooperaciones;
	}

	public void setCooperaciones(List<CooperacionDto> cooperaciones) {
		this.cooperaciones = cooperaciones;
	}

}