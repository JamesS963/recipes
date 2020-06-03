package com.springproject.recipes.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.recipes.model.Ingredient;
import com.springproject.recipes.model.Instruction;
import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;
import com.springproject.recipes.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/recipe")
@Slf4j
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@GetMapping
	public ModelAndView getCreateRecipe() {
		Recipe recipe = new Recipe();
		List<Ingredient> ingredients=new ArrayList<Ingredient>();
		ingredients.add(new Ingredient());
		
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(new Instruction());
		recipe.setIngredients(ingredients);
		recipe.setInstructions(instructions);
		
		return new ModelAndView("createRecipe", "recipe", recipe);
	}

	@PostMapping
	public ModelAndView postCreateRecipe(@Valid Recipe recipe, Errors errors, @AuthenticationPrincipal User user)
			throws Exception {

		if(errors.hasErrors()) {
			return new ModelAndView("createRecipe");
		}
		
		recipe.setAuthor(user);

		log.info("at controller");
		log.info(recipe.toString());
		recipeService.createRecipe(recipe);
		List<Recipe> recipes = recipeService.getAll();
		log.info(recipes.toString());
		return new ModelAndView("index", "recipes", recipes).addObject("message",
				"recipe " + recipe.getTitle() + " has been created.");
	}

}
