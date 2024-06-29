package comapigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comapigateway.requests.CursoServiceRequest;

@RestController
@RequestMapping("/api-curso")
public class CursoController {

	@Autowired
	CursoServiceRequest CursoServiceRequest;

	@PostMapping
	public ResponseEntity<?> saveCurso(@RequestBody Object curso) {
		return new ResponseEntity<>(CursoServiceRequest.saveCursos(curso), HttpStatus.CREATED);
	}

	@GetMapping("all")
	public ResponseEntity<?> getAllCursos() {

		return ResponseEntity.ok(CursoServiceRequest.getAllCursos());
	}
}
