package comapigateway.services;

import comapigateway.entities.User;

public interface AuthenticationService {
	User signInAndReturnJWT(User signInRequest);
}