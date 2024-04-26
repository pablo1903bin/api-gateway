package comapigateway.services;

import java.util.Optional;

import comapigateway.entities.User;
import comapigateway.models.Role;

public interface UserService {
	User saveUser(User user);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	void changeRole(Role newRole, String username);

	User findByUsernameReturnToken(String username);
}
