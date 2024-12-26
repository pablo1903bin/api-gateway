package comapigateway.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comapigateway.entities.User; // Clase que representa la entidad User.
import comapigateway.models.ApiResponse;
import comapigateway.services.AuthenticationService; // Servicio que maneja la lógica de autenticación.
import comapigateway.services.UserService; // Servicio que maneja la lógica relacionada con usuarios.

/**
 * Controlador REST para manejar las operaciones de autenticación.
 * Proporciona endpoints para el registro ("sign-up") y el inicio de sesión ("sign-in") de usuarios.
 */
@RestController
@RequestMapping("api/authentication") // Prefijo para todas las rutas definidas en este controlador.
public class AuthenticationController {

    // Logger para registrar mensajes relacionados con las operaciones de autenticación.
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    // Inyección del servicio de autenticación.
    @Autowired
    private AuthenticationService authenticationService;

    // Inyección del servicio de usuarios.
    @Autowired
    private UserService userService;

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param user Objeto User enviado en el cuerpo de la solicitud.
     * @return Respuesta con el usuario creado o un estado de conflicto si ya existe.
     */
    @PostMapping("sign-up") // Define una ruta POST para el registro de usuarios.
    public ResponseEntity<?> signUp(@RequestBody User user) {
        // Verifica si el nombre de usuario ya existe.
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            // Construye una respuesta con el código 404 y el mensaje de error.
            ApiResponse<String> response = new ApiResponse<>("409", "El usuario ya existe", null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        // Verifica si el correo electrónico ya existe.
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            ApiResponse<String> response = new ApiResponse<>("409", "El correo ya existe", null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // Guarda el usuario y devuelve su información con un estado de creación (201).
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Endpoint para iniciar sesión.
     *
     * @param user Objeto User enviado en el cuerpo de la solicitud.
     * @return Respuesta con el token JWT generado tras la autenticación.
     */
    @PostMapping("sign-in") // Define una ruta POST para el inicio de sesión.
    public ResponseEntity<?> signIn(@RequestBody User user) {
       // logger.info("Inicio de sesión...");
        // Llama al servicio de autenticación para validar al usuario y generar un JWT.
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}
