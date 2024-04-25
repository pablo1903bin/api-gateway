package com_users.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com_users.entities.User;
import com_users.models.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	// Query me permite actualizar la data del usuario con un determinado rol
	// Busco al usuario y le actualizo su rol
	// Actualiza el rol del usuario

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.role = :role WHERE u.username = :username")
	void updateRoleByUsername(@Param("username") String username, @Param("role") Role role);

}
/*
 * @Modifying Esta anotación se utiliza para indicar que la consulta modificará
 * el estado de la base de datos.
 * 
 * @Transactional: Esta anotación se utiliza para marcar un método como
 * transaccional. Esto significa que la operación dentro del método se ejecutará
 * en una transacción. Si la operación tiene éxito, los cambios se guardarán en
 * la base de datos; de lo contrario, se revertirá la transacción.
 * 
 * @Query("UPDATE User u SET u.role = :role WHERE u.username = :username"): Esta
 * anotación se utiliza para definir una consulta JPQL (Java Persistence Query
 * Language). En este caso, la consulta actualiza el rol de un usuario basado en
 * su nombre de usuario. La parte :role y :username son parámetros que se pasan
 * a la consulta y deben ser vinculados usando @Param.
 * 
 * @Param("username") String username y @Param("role") Role role: Estas
 * anotaciones se utilizan para vincular los parámetros de la consulta JPQL con
 * los parámetros del método. De esta manera, el valor de username y role
 * pasados al método se insertarán en la consulta en lugar de los parámetros
 * :username y :role. Esto evita la posibilidad de ataques de inyección SQL y
 * proporciona una forma segura de pasar parámetros a la consulta.
 */
