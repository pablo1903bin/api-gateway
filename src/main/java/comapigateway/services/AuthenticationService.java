package comapigateway.services;

import comapigateway.entities.User;
import comapigateway.models.UserDTO;

public interface AuthenticationService {
	UserDTO signInAndReturnJWT(User signInRequest);
}
