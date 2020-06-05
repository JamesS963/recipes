package com.springproject.recipes.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;
import com.springproject.recipes.security.SecurityConfig;
import com.springproject.recipes.service.RecipeService;
import com.springproject.recipes.service.UserDetailServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes= {UserController.class, UserDetailServiceImpl.class, RecipeService.class, SecurityConfig.class})
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecipeService recipeService;

	@MockBean
	private UserDetailServiceImpl userDetailsService;

	@Before
	public void setup() {

	}

	@Test
	public void testThatUserPageIsReturnedWhenUserExists() throws Exception {

		User user = new User();
		user.setId(1l);
		List<Recipe> recipes = new ArrayList<Recipe>();

		when(userDetailsService.loadById(1L)).thenReturn(Optional.of(user));
		when(recipeService.findByAuthor(1L)).thenReturn(recipes);

		mockMvc.perform(get("/user/1")).andExpect(status().isOk()).andExpect(view().name("user"))
				.andExpect(model().attribute("user", user)).andExpect(model().attribute("recipes", recipes));

	}

	@Test
	public void testThatPageMissingIsReturnedWhenUserDoesntExist() throws Exception {
		when(userDetailsService.loadById(1L)).thenReturn(Optional.empty());
		when(recipeService.findByAuthor(1L)).thenThrow(new Exception());
		mockMvc.perform(get("/user/1")).andExpect(status().isOk()).andExpect(view().name("pageNotFound"));
	}
}
