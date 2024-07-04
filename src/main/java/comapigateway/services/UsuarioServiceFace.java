package comapigateway.services;

import comapigateway.models.UsuarioRequestDto;

public interface UsuarioServiceFace {

	Boolean saveUser(UsuarioRequestDto user);

}
