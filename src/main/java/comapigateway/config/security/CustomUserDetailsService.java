package comapigateway.config.security;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*
		 * Aser la busqueda de un usuario despues setearlo dentro del contexto de mi app
		 */
		User user = userService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado:" + username));

		/*Un rol es un Authority para spring*/
		//Un Usuario puede tener varios roles
		Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

		UserPrincipal userPrincipal = new UserPrincipal();

		userPrincipal.setId(user.getId());
		userPrincipal.setAuthorities(authorities);
		userPrincipal.setPassword(user.getPassword());
		userPrincipal.setUser(user);
		userPrincipal.setUsername(username);

		return userPrincipal;
	}
}