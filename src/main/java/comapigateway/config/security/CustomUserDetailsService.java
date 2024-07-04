package comapigateway.config.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comapigateway.entities.User;
import comapigateway.services.UserService;
import comapigateway.utils.SecurityUtils;

/*UserDetailsService lo uso para personalizar los metodos de busqueda del usuario*/
@Service
public class CustomUserDetailsService implements UserDetailsService {

	/* Este es mi servicio que me permite buscar usuarios dentro de mi DB */
	@Autowired
	private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscar el usuario en el servicio de usuarios
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado: " + username));

        // Crear un conjunto de autoridades (roles) para el usuario
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(SecurityUtils.convertToAuthority(user.getRole().name()));

        // Crear un objeto UserPrincipal y setear los detalles del usuario
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(user.getId());
        userPrincipal.setAuthorities(authorities);
        userPrincipal.setPassword(user.getPassword());
        userPrincipal.setUser(user);
        userPrincipal.setUsername(username);

        return userPrincipal;
	}
}