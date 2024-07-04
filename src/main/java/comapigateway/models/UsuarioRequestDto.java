package comapigateway.models;

public class UsuarioRequestDto {

	public String nombreUsuario;
	public String contraseña;

	public UsuarioRequestDto() {

	}

	public UsuarioRequestDto(String nombreUsuario, String contraseña) {

		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
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

	@Override
	public String toString() {
		return "UsuarioRequestDto [nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + "]";
	}

}
