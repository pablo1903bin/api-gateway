package comapigateway.services_impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comapigateway.config.security.UserPrincipal;
import comapigateway.entities.User;
import comapigateway.models.UserDTO;
import comapigateway.repositories.UserRepository;
import comapigateway.security.jwt.JwtProvider;
import comapigateway.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO signInAndReturnJWT(User signInRequest) {

	    logger.info("validando usuario entrante...");
	    logger.info("Usuario: " + signInRequest.getUsername());
	    logger.info("Pass: " + signInRequest.getPassword());

	    User user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(
	            () -> new UsernameNotFoundException("El usuario no fue encontrado:" + signInRequest.getEmail()));

	    logger.info("usuario si existe..   *******************  ");
	    logger.info("Usuario encontrado:  " + user.toString());

	    UsernamePasswordAuthenticationToken authenticationToken =
	            new UsernamePasswordAuthenticationToken(user.getUsername(), signInRequest.getPassword());

	    Authentication authentication = authenticationManager.authenticate(authenticationToken);
	    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
	    String jwt = jwtProvider.generateToken(userPrincipal);

	    User authenticatedUser = userPrincipal.getUser();
	    authenticatedUser.setToken(jwt);

	    // Crear y devolver un UserDTO en lugar de la entidad User
	    return new UserDTO(
	        authenticatedUser.getId(),
	        authenticatedUser.getUsername(),
	        authenticatedUser.getName(),
	        authenticatedUser.getApellido(),
	        authenticatedUser.getTelefono(),
	        authenticatedUser.getEmail(),
	        authenticatedUser.getFechaCreacion(),
	        authenticatedUser.getRole(),
	        authenticatedUser.getToken()
	    );
	}

}
