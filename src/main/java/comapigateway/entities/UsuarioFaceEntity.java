package comapigateway.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios_face")
public class UsuarioFaceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "usuario", unique = true, nullable = false, length = 100)
	private String nombreUsuario;

	@Column(name = "contraseña", unique = true, nullable = false, length = 100)
	private String contraseña;

	@Column(name = "fecha_creacion", nullable = false)
	private LocalDateTime fechaCreacion;

	public UsuarioFaceEntity() {
	}

	public UsuarioFaceEntity(Long id, String nombreUsuario, String contraseña, LocalDateTime fechaCreacion) {

		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.fechaCreacion = fechaCreacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "UsuarioEntity [id=" + id + ", nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

}
