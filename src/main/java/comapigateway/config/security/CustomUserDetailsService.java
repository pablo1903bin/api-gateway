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

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado:" + username));

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