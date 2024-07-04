package comapigateway.services_impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comapigateway.entities.UsuarioFaceEntity;
import comapigateway.models.UsuarioRequestDto;
import comapigateway.repositories.UsuarioFaceRepository;
import comapigateway.services.UsuarioServiceFace;

@Service
public class UsuarioServiceFaceImpl implements UsuarioServiceFace {

	@Autowired
	UsuarioFaceRepository usuarioFaceRepository;

	@Override
	public Boolean saveUser(UsuarioRequestDto user) {
		try {
			UsuarioFaceEntity usuarioModel = new UsuarioFaceEntity();
			usuarioModel.setNombreUsuario(user.getNombreUsuario());
			usuarioModel.setContraseña(user.getContraseña());
			usuarioModel.setFechaCreacion(LocalDateTime.now());

			// Guardar la entidad y recibir el resultado
			UsuarioFaceEntity savedUser = usuarioFaceRepository.save(usuarioModel);

			// Verificar si se guardó correctamente
			if (savedUser != null && savedUser.getId() != null) {
				return true; // Indica que se guardó correctamente
			} else {
				return false; // Indica que hubo algún problema al guardar
			}
		} catch (Exception e) {
			// Manejo de excepciones si ocurre algún error al guardar
			e.printStackTrace(); // Considera un manejo más adecuado de las excepciones
			return false;
		}
	}

}
