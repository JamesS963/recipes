package com.springproject.recipes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.recipes.data.RecipeRepository;
import com.springproject.recipes.data.UserRepository;
import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public Recipe createRecipe(Recipe recipe) throws Exception {
		return recipeRepo.save(recipe);
	}

	@Override
	public List<Recipe> getAll() {
		List<Recipe> recipes = (List<Recipe>) recipeRepo.findAll();
		return recipes;
	}

	@Override
	public Optional<Recipe> get(Long id) {
		return recipeRepo.findById(id);
	}

	@Override
	public List<Recipe> findByAuthor(Long id) throws Exception {
		Optional<User> user = userRepo.findById(id);
		
		if(user.isEmpty()) {
			throw new Exception("User does not exist");
		}
		
		return recipeRepo.findByAuthor(user.get());
	}

}
