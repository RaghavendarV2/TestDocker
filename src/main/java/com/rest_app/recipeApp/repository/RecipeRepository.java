package com.rest_app.recipeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest_app.recipeApp.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	List<Recipe> findByCategoryCategoryName(String categoryName);
}
