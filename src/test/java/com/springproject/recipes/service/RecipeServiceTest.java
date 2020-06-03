package com.springproject.recipes.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.springproject.recipes.data.RecipeRepository;
import com.springproject.recipes.model.Recipe;

@RunWith(SpringRunner.class)
public class RecipeServiceTest {

	@InjectMocks
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testRecipeIsSavedToDatabase() throws Exception {
		Recipe recipe = new Recipe();
		
		
		
		when(recipeRepo.save(recipe)).thenReturn(recipe);
		
		Recipe createdRecipe = recipeService.createRecipe(recipe);
		assertThat(createdRecipe).isNotNull();
		
		
	}
	
	
	@Test
	public void testAllRecipesAreReturnedWhenGetAllIsCalled() throws Exception{
		List<Recipe> recipes = new ArrayList<Recipe>();
		Recipe recipeOne= new Recipe();
		Recipe recipeTwo = new Recipe();
		Recipe recipeThree = new Recipe();
		
		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);
		
		when(recipeRepo.findAll()).thenReturn(recipes);
		
		assertEquals(recipeService.getAll().size(), 3);
		
	}
	
}
