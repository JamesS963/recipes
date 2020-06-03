package com.springproject.recipes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.recipes.data.RecipeRepository;
import com.springproject.recipes.model.Recipe;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepo;

	@Override
	public Recipe createRecipe(Recipe recipe) throws Exception {
		return recipeRepo.save(recipe);
	}

	@Override
	public List<Recipe> getAll() {
		 List<Recipe> recipes = (List<Recipe>) recipeRepo.findAll();
		 return recipes;
	}

}
