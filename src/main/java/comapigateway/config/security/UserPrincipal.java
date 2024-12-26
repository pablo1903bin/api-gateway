package comapigateway.config.security;

import org.springframework.security.core.GrantedAuthority; // Representa permisos o roles asignados al usuario.
import org.springframework.security.core.userdetails.UserDetails; // Interfaz para proporcionar detalles del usuario.

import comapigateway.entities.User; // Entidad que representa al usuario en la base de datos.

import java.util.Collection; // Representa una colección de permisos.
import java.util.Set; // Representa un conjunto de permisos.

/**
 * Clase personalizada que implementa la interfaz UserDetails de Spring Security.
 * Se utiliza para representar al usuario autenticado y sus roles o permisos.
 */
public class UserPrincipal implements UserDetails {

    // SerialVersionUID para la serialización de esta clase.
    private static final long serialVersionUID = 1L;

    /* Atributos personalizados basados en la entidad User. */
    private Long id; // ID único del usuario.
    private String username; // Nombre de usuario.
    transient private String password; // Contraseña del usuario (transient para evitar su serialización).
    transient private User user; // Referencia a la entidad User (opcional y no serializable).
    private Set<GrantedAuthority> authorities; // Roles o permisos asignados al usuario.

    /**
     * Constructor por defecto.
     */
    public UserPrincipal() {
    }

    /**
     * Constructor completo para inicializar todos los campos.
     *
     * @param id          ID del usuario.
     * @param username    Nombre de usuario.
     * @param password    Contraseña del usuario.
     * @param user        Entidad User asociada.
     * @param authorities Conjunto de roles o permisos.
     */
    public UserPrincipal(Long id, String username, String password, User user, Set<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }

    /**
     * @return ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id Establece el ID del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Entidad User asociada.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user Establece la entidad User asociada.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @param username Establece el nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password Establece la contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param authorities Establece los roles o permisos del usuario.
     */
    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * @return Colección de roles o permisos asignados al usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @return Contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @return Nombre de usuario.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return true si la cuenta no está expirada.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return true si la cuenta no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return true si las credenciales no están expiradas.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return true si la cuenta está habilitada.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Representación en cadena del objeto UserPrincipal.
     *
     * @return Cadena que contiene detalles básicos del usuario.
     */
    @Override
    public String toString() {
        return "UserPrincipal [id=" + id + ", username=" + username + ", authorities=" + authorities + "]\n";
    }
}
