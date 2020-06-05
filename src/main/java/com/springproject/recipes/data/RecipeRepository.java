package com.springproject.recipes.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.recipes.model.Recipe;
import com.springproject.recipes.model.User;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe> findByAuthor(User author);
	
}
