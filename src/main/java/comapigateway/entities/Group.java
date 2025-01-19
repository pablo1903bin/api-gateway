package comapigateway.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Column(columnDefinition = "text")
    private String descripcion;

    @NotBlank(message = "El tipo no puede estar vacío")
    @Size(max = 50, message = "El tipo no puede exceder los 50 caracteres")
    @Column(nullable = false, length = 50, columnDefinition = "varchar(50) default 'escolar'")
    private String tipo;

    @Size(max = 50, message = "La categoría no puede exceder los 50 caracteres")
    @Column(length = 50)
    private String categoria;

    @Min(value = 1, message = "El número máximo de miembros debe ser al menos 1")
    @Column(name = "max_miembros")
    private Integer maxMiembros;

    @NotNull(message = "El valor de esPublico no puede ser nulo")
    @Column(name = "es_publico", nullable = false, columnDefinition = "boolean default true")
    private Boolean esPublico;

    @NotNull(message = "El ID del creador no puede ser nulo")
    @Column(name = "creado_por", nullable = false)
    private Long creadoPor;

    @NotNull(message = "La fecha de creación no puede ser nula")
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @NotNull(message = "La fecha de actualización no puede ser nula")
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @NotBlank(message = "El estatus no puede estar vacío")
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estatus debe ser 'activo', 'inactivo' o 'pendiente'")
    @Column(length = 20, columnDefinition = "varchar(20) default 'activo'")
    private String estatus;

    @Size(max = 255, message = "La ubicación no puede exceder los 255 caracteres")
    private String ubicacion;

    @Column(columnDefinition = "jsonb")
    private String metadata;
}
// 
//

//
//
