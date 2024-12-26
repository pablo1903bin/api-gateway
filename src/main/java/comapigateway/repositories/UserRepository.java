package comapigateway.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; // Proporciona métodos básicos para operaciones CRUD.
import org.springframework.data.jpa.repository.Modifying; // Indica que una consulta modifica datos.
import org.springframework.data.jpa.repository.Query; // Permite escribir consultas personalizadas.
import org.springframework.data.repository.query.Param; // Permite el uso de parámetros nombrados en consultas.

import comapigateway.entities.User; // Entidad que representa la tabla de usuarios.
import comapigateway.models.Role; // Enumeración que representa los roles en el sistema.

/**
 * Repositorio para manejar la entidad User.
 * Extiende JpaRepository, proporcionando métodos básicos para operaciones CRUD
 * y soporte para consultas personalizadas.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario (username).
     *
     * @param username Nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si existe, o vacío si no.
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email Correo electrónico a buscar.
     * @return Un Optional que contiene el usuario si existe, o vacío si no.
     */
    Optional<User> findByEmail(String email);

    /**
     * Busca un usuario por su nombre de usuario utilizando una consulta nativa.
     * Esta consulta usa SQL directamente en lugar de JPQL.
     *
     * @param username Nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si existe, o vacío si no.
     */
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Optional<User> findByEmailNative(@Param("username") String username);

    /**
     * Actualiza el rol de un usuario dado su nombre de usuario.
     * Esta consulta utiliza JPQL para realizar la actualización.
     *
     * @param username Nombre de usuario al que se le cambiará el rol.
     * @param role     Nuevo rol a asignar.
     */
    @Modifying // Indica que esta consulta modifica datos en la base de datos.
    @Query("update User set role=:role where username=:username") // JPQL para actualizar el rol.
    void updateUserRole(@Param("username") String username, @Param("role") Role role);

}
