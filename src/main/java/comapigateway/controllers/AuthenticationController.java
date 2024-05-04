package comapigateway.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comapigateway.entities.User;
import comapigateway.services.AuthenticationService;
import comapigateway.services.UserService;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;

	@PostMapping("sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user) {

		//Si el nombre de usuario ya existe entonces lanzar un conflicto
		if (userService.findByUsername(user.getUsername()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		//Si el correo ya existe entonces lanzar un conflicto
		if (userService.findByEmail(user.getEmail()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@PostMapping("sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user) {
		System.out.println("Iniciando login...");
		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
	}

}