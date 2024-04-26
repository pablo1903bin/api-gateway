package comapigateway.services_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comapigateway.config.security.UserPrincipal;
import comapigateway.entities.User;
import comapigateway.repositories.UserRepository;
import comapigateway.security.jwt.JwtProvider;
import comapigateway.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User signInAndReturnJWT(User signInRequest) {
		User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
				() -> new UsernameNotFoundException("El usuario no fue encontrado:" + signInRequest.getEmail()));

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), signInRequest.getPassword()));

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String jwt = jwtProvider.generateToken(userPrincipal);

		User sigInUser = userPrincipal.getUser();
		sigInUser.setToken(jwt);

		return sigInUser;
	}

}
