package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comapigateway.config.security.UserPrincipal; // Clase que representa al usuario autenticado.
import comapigateway.models.Role; // Enumeración que representa los roles disponibles.
import comapigateway.services.UserService; // Servicio para manejar lógica relacionada con usuarios.

/**
 * Controlador REST para manejar operaciones relacionadas con los usuarios.
 * Define endpoints para cambiar el rol de un usuario y obtener la información del usuario actual.
 */
@RestController // Indica que esta clase es un controlador REST y devuelve respuestas JSON.
@RequestMapping("/api/user") // Prefijo de la URL para todos los endpoints de este controlador.
public class UserController {

    // Inyección del servicio UserService para manejar la lógica de negocio relacionada con usuarios.
    @Autowired
    private UserService userService;

    /**
     * Endpoint para cambiar el rol de un usuario autenticado.
     *
     * @param userPrincipal El usuario actualmente autenticado (inyectado automáticamente).
     * @param role El nuevo rol que se asignará al usuario.
     * @return Una respuesta con estado 200 (OK) si el cambio fue exitoso.
     */
    @PutMapping("change/{role}") // Define una ruta PUT con un parámetro de rol.
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role) {
        // Cambia el rol del usuario autenticado utilizando el servicio.
        userService.changeRole(role, userPrincipal.getUsername());
        System.out.println("Actualizando rol..."); // Registro en consola para fines de depuración.
        return ResponseEntity.ok(true); // Devuelve una respuesta exitosa con un valor booleano.
    }

    /**
     * Endpoint para obtener la información del usuario actualmente autenticado.
     *
     * @param userPrincipal El usuario actualmente autenticado (inyectado automáticamente).
     * @return Una respuesta con la información del usuario y estado 200 (OK).
     */
    @GetMapping // Define una ruta GET sin parámetros adicionales.
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        // Busca al usuario en la base de datos y genera un token actualizado.
        return new ResponseEntity<>(userService.findByUsernameReturnToken(userPrincipal.getUsername()), HttpStatus.OK);
    }
}
