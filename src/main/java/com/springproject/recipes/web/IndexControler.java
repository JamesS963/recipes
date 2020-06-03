package com.springproject.recipes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.recipes.service.RecipeService;

@Controller
public class IndexControler {

	@Autowired
	RecipeService recipeService;

	@GetMapping
	public ModelAndView getIndexpage() {

		return new ModelAndView("index").addObject("recipes", recipeService.getAll());

	}

}
