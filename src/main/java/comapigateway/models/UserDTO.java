package comapigateway.models;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDateTime fechaCreacion;
    private Role role;
    private String token;

    public UserDTO() {}

	public UserDTO(Long id, String username, String name, String apellido, String telefono, String email,
			LocalDateTime fechaCreacion, Role role, String token) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.fechaCreacion = fechaCreacion;
		this.role = role;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", name=" + name + ", apellido=" + apellido
				+ ", telefono=" + telefono + ", email=" + email + ", fechaCreacion=" + fechaCreacion + ", role=" + role
				+ ", token=" + token + "]";
	}
    

}