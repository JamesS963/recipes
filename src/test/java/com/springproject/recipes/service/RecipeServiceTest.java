package com.springproject.recipes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.springproject.recipes.data.RecipeRepository;
import com.springproject.recipes.data.UserRepository;
import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;

@RunWith(SpringRunner.class)
public class RecipeServiceTest {

	@InjectMocks
	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepo;

	@Mock
	UserRepository userRepo;

	List<Recipe> recipes;

	@Before
	public void init() {
		recipes = new ArrayList<Recipe>();
		Recipe recipeOne = new Recipe();
		Recipe recipeTwo = new Recipe();
		Recipe recipeThree = new Recipe();

		recipes.add(recipeOne);
		recipes.add(recipeTwo);
		recipes.add(recipeThree);

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
	public void testAllRecipesAreReturnedWhenGetAllIsCalled() throws Exception {

		when(recipeRepo.findAll()).thenReturn(recipes);

		assertEquals(recipeService.getAll().size(), 3);

	}

	@Test
	public void testRecipeIsReturnedWhenRecipeExists() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1l);
		when(recipeRepo.findById(1L)).thenReturn(Optional.of(recipe));

		assertEquals(recipe, recipeService.get(1l).get());
	}

	@Test
	public void testRecipeOptionalIsEmptyWhenRecipeIsCalledThatDoesNotExist() throws Exception {
		assertTrue(recipeService.get(1l).isEmpty());
	}

	@Test(expected = Exception.class)
	public void testExceptionIsThrownWhenGetByUserIsCalledWithNoUserFound() throws Exception {
		recipeService.findByAuthor(1l);
	}

	@Test
	public void TestUsersRecipesAreReturnedWhenFindByAuthorIsCalled() throws Exception {
		User user = new User("User", "password");
		user.setId(1l);

		when(userRepo.findById(1L)).thenReturn(Optional.of(user));
		when(recipeRepo.findByAuthor(user)).thenReturn(recipes);

		assertEquals(3, recipeService.findByAuthor(1L).size());
	}

}
