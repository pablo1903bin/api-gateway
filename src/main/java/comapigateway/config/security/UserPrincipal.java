package comapigateway.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import comapigateway.entities.User;

import java.util.Collection;
import java.util.Set;



/*Esta clase se unira con la estructura de spring sec y pasara a ser mi clase personalizada de un usuario */
/*El UserDetails es una interfaz que se conecta a la Db para obtener la informacion del usuario que se esta logueando*/


public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	/* Agrgo los datos que tengo dentro de mi DB Users */
	private Long id;
	private String username;
	transient private String password;
	transient private User user;
	private Set<GrantedAuthority> authorities;

	public UserPrincipal() {
	};

	public UserPrincipal(Long id, String username, String password, User user, Set<GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "UserPrincipal [id=" + id + ", username=" + username + ", authorities=" + authorities + "]\n";
	}

}
