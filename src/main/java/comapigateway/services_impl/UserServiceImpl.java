package comapigateway.services_impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import comapigateway.entities.User; // Entidad que representa un usuario.
import comapigateway.models.Role; // Enumeración de roles disponibles en la aplicación.
import comapigateway.repositories.UserRepository; // Repositorio para interactuar con la base de datos de usuarios.
import comapigateway.security.jwt.JwtProvider; // Proveedor de JWT para generación y validación de tokens.
import comapigateway.services.UserService; // Interfaz de servicio para usuarios.

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder; // Utilizado para encriptar contraseñas.

    @Autowired
    private JwtProvider jwtProvider; // Proveedor para generar tokens JWT.

    /**
     * Guarda un nuevo usuario en la base de datos, encripta su contraseña,
     * le asigna un rol por defecto, y genera un token JWT asociado.
     *
     * @param user El usuario a guardar.
     * @return El usuario guardado con su token JWT.
     */
    @Override
    public User saveUser(User user) {
        // Encripta la contraseña antes de guardarla.
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Asigna el rol por defecto como "USER".
        user.setRole(Role.USER);

        // Establece la fecha de creación con la fecha y hora actual del servidor.
        user.setFechaCreacion(LocalDateTime.now());

        // Guarda el usuario en la base de datos.
        User userCreated = userRepository.save(user);

        // Genera un token JWT para el usuario creado.
        String jwt = jwtProvider.generateToken(userCreated);
        userCreated.setToken(jwt); // Asigna el token al usuario.

        // Devuelve el usuario creado con el token asignado.
        return userCreated;
    }

    /**
     * Busca un usuario por su nombre de usuario (username).
     *
     * @param username El nombre de usuario a buscar.
     * @return Un Optional con el usuario si existe, o vacío si no.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email El correo electrónico a buscar.
     * @return Un Optional con el usuario si existe, o vacío si no.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Cambia el rol de un usuario dado su nombre de usuario.
     *
     * @param newRole  El nuevo rol a asignar.
     * @param username El nombre de usuario al que se le cambiará el rol.
     */
    @Transactional // Asegura que el cambio de rol sea atómico.
    @Override
    public void changeRole(Role newRole, String username) {
        userRepository.updateUserRole(username, newRole);
    }

    /**
     * Busca un usuario por su nombre de usuario y genera un nuevo token JWT asociado.
     *
     * @param username El nombre de usuario a buscar.
     * @return El usuario encontrado con un token JWT actualizado.
     * @throws UsernameNotFoundException Si el usuario no existe.
     */
    @Override
    public User findByUsernameReturnToken(String username) {
        // Busca el usuario en la base de datos.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe:" + username));

        // Genera un nuevo token JWT para el usuario encontrado.
        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt); // Asigna el token al usuario.

        // Devuelve el usuario con el token actualizado.
        return user;
    }
}
