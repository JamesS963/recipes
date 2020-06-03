package com.springproject.recipes.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.recipes.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
