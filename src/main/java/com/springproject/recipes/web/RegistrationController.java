package com.springproject.recipes.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.recipes.formobject.RegistrationForm;
import com.springproject.recipes.service.UserDetailServiceImpl;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	UserDetailServiceImpl userDetailSercvice;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping
	public String registerForm() {
		return "register";
	}

	@PostMapping
	public String processRegistration(@Valid RegistrationForm form, Errors errors) throws Exception {

		if (errors.hasErrors()) {
			return "register";
		}

		userDetailSercvice.createUser(form.toUser(passwordEncoder));

		return "redirect:/login";
	} 

}
