package comapigateway.models;

public class ApiResponse<T> {
    private String codigo; // Código de estado de la respuesta 
    private String mensaje; // Mensaje descriptivo de la respuesta
    private T data; // Datos de la respuesta (puede ser cualquier tipo)

    // Constructor vacío
    public ApiResponse() {}

    // Constructor con parámetros
    public ApiResponse(String codigo, String mensaje, T data) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.data = data;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "ApiResponse [codigo=" + codigo + ", mensaje=" + mensaje + ", data=" + data + "]";
	}
    
    
}
