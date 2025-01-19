package comapigateway.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caja")
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "saldo_actual", nullable = false, columnDefinition = "float8 default 0")
    private Double saldoActual = 0.0;

    @Column(name = "ingresos_totales", nullable = false, columnDefinition = "float8 default 0")
    private Double ingresosTotales = 0.0;

    @Column(name = "egresos_totales", nullable = false, columnDefinition = "float8 default 0")
    private Double egresosTotales = 0.0;

    @Column(name = "ultima_actualizacion", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime ultimaActualizacion;

    @Column(name = "creado_en", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime creadoEn;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}