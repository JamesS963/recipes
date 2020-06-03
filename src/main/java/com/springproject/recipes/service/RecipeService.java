package com.springproject.recipes.service;

import java.util.List;

import com.springproject.recipes.model.Recipe;

public interface RecipeService {
	public Recipe createRecipe(Recipe recipe) throws Exception;
	public List<Recipe> getAll();
}
