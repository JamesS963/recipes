package com.springproject.recipes.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;
import com.springproject.recipes.service.RecipeService;
import com.springproject.recipes.service.UserDetailServiceImpl;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserDetailServiceImpl userService;

	@GetMapping("/{userID}")
	public ModelAndView getUser(@PathVariable("userID") Long id) {

		Optional<User> user = userService.loadById(id);
		List<Recipe> recipes;
		try {
			recipes = recipeService.findByAuthor(id);
		} catch (Exception e) {
			return new ModelAndView("pageNotFound");
		}
		return new ModelAndView("user", "user", user.get()).addObject("recipes", recipes);
	}
}
