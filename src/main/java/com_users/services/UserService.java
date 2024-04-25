package com_users.services;

import com_users.entities.User;

public interface UserService {
	
	User findById(Integer IdUser);

	User saveUser(User user);
}
