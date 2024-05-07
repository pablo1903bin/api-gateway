package comapigateway.services_impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import comapigateway.entities.User;
import comapigateway.models.Role;
import comapigateway.repositories.UserRepository;
import comapigateway.security.jwt.JwtProvider;
import comapigateway.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtProvider;

	/*Este metodo me retornara el user guardado con su token*/
	@Override
	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));//Encripto el pass
		user.setRole(Role.USER); //Por defecto los nuevo seran Solo Users
		user.setFechaCreacion(LocalDateTime.now());  //Fecha actual del servidor 

		User userCreated = userRepository.save(user);

		String jwt = jwtProvider.generateToken(userCreated);
		userCreated.setToken(jwt);

		return userCreated;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	@Override
	public void changeRole(Role newRole, String username) {
		userRepository.updateUserRole(username, newRole);
	}

	@Override
	public User findByUsernameReturnToken(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no existe:" + username));

		String jwt = jwtProvider.generateToken(user);
		user.setToken(jwt);
		return user;
	}

}
