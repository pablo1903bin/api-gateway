package comapigateway.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import comapigateway.models.ApiResponse;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de validación en los DTOs.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        // Loguea el error
        ex.printStackTrace();

        ApiResponse<String> response = new ApiResponse<>("400", errorMessage, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja errores cuando una entidad no es encontrada.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ex.printStackTrace();
        ApiResponse<String> response = new ApiResponse<>("404", "Entidad no encontrada", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja errores relacionados con la base de datos.
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<String>> handleDataAccessException(DataAccessException ex) {
        ex.printStackTrace();
        ApiResponse<String> response = new ApiResponse<>("500", "Error de base de datos", "Contacta al administrador.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja errores cuando un elemento no se encuentra en una lista.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        ex.printStackTrace();
        ApiResponse<String> response = new ApiResponse<>("404", "Elemento no encontrado", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Maneja cualquier otro error genérico no capturado.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        ex.printStackTrace();
        ApiResponse<String> response = new ApiResponse<>("500", "Error inesperado", "Ocurrió un problema interno.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}
