package com.springproject.recipes.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springproject.recipes.data.RecipeRepository;
import com.springproject.recipes.data.UserRepository;
import com.springproject.recipes.model.Ingredient;
import com.springproject.recipes.model.Instruction;
import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;
import com.springproject.recipes.service.RecipeService;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RecipeRepository recipeRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RecipeService recipeService;
	
	Recipe recipe;
	
	@Before
	public void setup() {
		when(userRepository.findByUsername("testUser")).thenReturn(new User("TestUser", "testPassword"));
		recipe = new Recipe();
		List<Ingredient> ingredients=new ArrayList<Ingredient>();
		ingredients.add(new Ingredient());
		
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(new Instruction());
		recipe.setIngredients(ingredients);
		recipe.setInstructions(instructions);
	}
	
	@Test
	@WithMockUser(username="testUser",password="testPassword")
	public void testDisplayCreateRecipeForm() throws Exception {
		
		
		mockMvc.perform(get("/recipe")).andExpect(status().isOk()).andExpect(view().name("createRecipe")).andExpect(model().attribute("recipe", recipe));
	}
	
	
	@Test
	@WithMockUser(username="testUser",password="testPassword")
	public void testPostCreateRecipeFormWithInValidData() throws Exception{
		
		mockMvc.perform(post("/recipe").flashAttr("recipe", recipe).with(csrf())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is2xxSuccessful());
	
	}
}
