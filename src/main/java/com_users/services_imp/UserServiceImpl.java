package com_users.services_imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com_users.entities.User;
import com_users.repositories.UserRepository;
import com_users.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findById(Integer IdUser) {
		
		
		return null;
	}

	@Override
	public User saveUser(User user) {
		
		return null;
	}

}
