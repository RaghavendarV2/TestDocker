package com.rest_app.recipeApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.model.Recipe;
import com.rest_app.recipeApp.repository.CategoryRepository;
import com.rest_app.recipeApp.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Recipe getRecipeById(Long id) {
		return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
	}

	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	public Recipe createRecipe(Recipe recipe) {
		Long categoryId = recipe.getCategory().getCategoryId();
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found!"));

		recipe.setCategory(category);

		if (recipe.getIngredients() != null) {
			recipe.getIngredients().forEach(i -> i.setRecipe(recipe));
		}

		return recipeRepository.save(recipe);
	}

	public Recipe updateRecipe(Long id, Recipe updated) {
		Recipe existing = getRecipeById(id);

		if (updated.getRecipeName() != null) {
			existing.setRecipeName(updated.getRecipeName());
		}
		if (updated.getDescription() != null) {
			existing.setDescription(updated.getDescription());
		}
		if (updated.getCookingTime() != 0) {
			existing.setCookingTime(updated.getCookingTime());
		}

		if (updated.getCategory() != null) {
			Category category = categoryRepository.findById(updated.getCategory().getCategoryId())
					.orElseThrow(() -> new RuntimeException("Category not found"));

			existing.setCategory(category);
		}

		if (updated.getIngredients() != null) {
			existing.getIngredients().clear();
			updated.getIngredients().forEach(i -> i.setRecipe(existing));
			existing.getIngredients().addAll(updated.getIngredients());
		}

		return recipeRepository.save(existing);
	}
	
	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id);
	}
	
	public List<Recipe> getRecipesByCategoryName(String categoryName) {
	    return recipeRepository.findByCategoryCategoryName(categoryName);
	}
}
