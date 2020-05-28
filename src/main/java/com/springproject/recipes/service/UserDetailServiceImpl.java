package com.springproject.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springproject.recipes.data.UserRepository;
import com.springproject.recipes.model.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository userRepo;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException("User '" + username + "'not found");
	}

	public User createUser(User user) throws Exception {

		if (userRepo.findByUsername(user.getUsername()) != null) {
			throw new Exception("account with username '" + user.getUsername() + "' already exists");
		}

		return userRepo.save(user);
	}

}
