package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comapigateway.models.UsuarioRequestDto;
import comapigateway.services.UsuarioServiceFace;

@RestController
@RequestMapping("/authentication")
public class UsuarioFaceController {

	@Autowired
	UsuarioServiceFace usuarioService;

	@PostMapping("/login")
	public ResponseEntity<String> signIn(@RequestBody UsuarioRequestDto user) {
		boolean saved = usuarioService.saveUser(user);

		if (saved) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear el usuario");
		}
	}
}
