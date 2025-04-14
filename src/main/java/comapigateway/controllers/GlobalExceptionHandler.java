package comapigateway.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import comapigateway.models.ApiResponse; // Clase que estructura las respuestas API.

import javax.persistence.EntityNotFoundException;

import java.util.NoSuchElementException;

/**
 * Controlador global para manejar excepciones de manera centralizada en la aplicación.
 * Utiliza la anotación @RestControllerAdvice para aplicar automáticamente los manejadores de excepciones
 * a todos los controladores REST.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja errores de validación en los DTOs.
     *
     * @param ex Excepción de validación de argumentos.
     * @return Respuesta con código de estado 400 (Bad Request) y detalles del error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Obtiene el primer error de validación.
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        // Registra el error en los logs.
        ex.printStackTrace();

        // Construye una respuesta con el código 400 y el mensaje de error.
        ApiResponse<String> response = new ApiResponse<>("400", errorMessage, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja errores cuando una entidad no es encontrada.
     *
     * @param ex Excepción lanzada cuando no se encuentra una entidad.
     * @return Respuesta con código de estado 404 (Not Found) y detalles del error.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        // Registra el error en los logs.
        ex.printStackTrace();

        // Construye una respuesta con el código 404 y el mensaje de error.
        ApiResponse<String> response = new ApiResponse<>("404", "Entidad no encontrada", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja errores relacionados con la base de datos.
     *
     * @param ex Excepción lanzada por problemas con la base de datos.
     * @return Respuesta con código de estado 500 (Internal Server Error) y detalles del error.
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<String>> handleDataAccessException(DataAccessException ex) {
        // Registra el error en los logs.
        ex.printStackTrace();

        // Construye una respuesta con el código 500 y un mensaje genérico.
        ApiResponse<String> response = new ApiResponse<>("500", "Error de base de datos", "Contacta al administrador.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja errores cuando un elemento no se encuentra en una lista o colección.
     *
     * @param ex Excepción lanzada cuando un elemento no es encontrado.
     * @return Respuesta con código de estado 404 (Not Found) y detalles del error.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        // Registra el error en los logs.
        ex.printStackTrace();

        // Construye una respuesta con el código 404 y el mensaje de error.
        ApiResponse<String> response = new ApiResponse<>("ERROR", "Elemento no encontrado", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Maneja errores cuando un usuario no es encontrado.
     *
     * @param ex Excepción UsernameNotFoundException lanzada cuando no se encuentra el usuario.
     * @return Respuesta con código de estado 404 (Not Found) y detalles del error.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        // Registra solo un mensaje relevante sin el stack trace completo.
        //logger.error("Error: Usuario no encontrado. Detalle: {}", ex.getMessage());

        // Construye una respuesta con el código 404 y el mensaje de error.
        ApiResponse<String> response = new ApiResponse<>("ERROR", ex.getMessage(), null );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {
        ApiResponse<String> response = new ApiResponse<>("ERROR", "Credenciales inválidas: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<String> response = new ApiResponse<>("ERROR", "Error inesperado: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    /**
     * Maneja cualquier otro error genérico no capturado por los manejadores específicos.
     *
     * @param ex Excepción genérica.
     * @return Respuesta con código de estado 500 (Internal Server Error) y un mensaje genérico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        // Registra el error en los logs.
        ex.printStackTrace();

        // Construye una respuesta con el código 500 y un mensaje genérico.
        ApiResponse<String> response = new ApiResponse<>("500", "Error inesperado", "Ocurrió un problema interno.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
