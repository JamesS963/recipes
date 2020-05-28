package com.springproject.recipes.formobject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.springproject.recipes.model.User;

import lombok.Data;

@Data
public class RegistrationForm {
	@NotNull
	@Size(min = 4, max = 15, message = "Username must be at least 5 charachters long and no more than 15 charachters")
	private String username;
	@NotNull
	@Size(min = 6)
	private String password;

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(this.username, passwordEncoder.encode(this.password));
	}
}
