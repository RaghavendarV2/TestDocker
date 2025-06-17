package com.rest_app.recipeApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.model.Ingredient;
import com.rest_app.recipeApp.model.Recipe;
import com.rest_app.recipeApp.repository.IngredientRepository;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
public class IngredientServiceTest {
	
	@Mock
	private IngredientRepository ingredientRepository;
	
	@InjectMocks
	private IngredientService ingredientService;
	
	private Ingredient ingredient;
	private Ingredient ingredient2;
	
	@BeforeEach
	public void setuptest() {
		Category c1 = new Category();
		c1.setCategoryName("Tasty");
		
		Recipe r1 = new Recipe();
		r1.setRecipeName("potate sides");
		r1.setDescription("Good");
		r1.setCookingTime(10);
		r1.setCategory(c1);
		
		
		ingredient = new Ingredient();
        ingredient.setIngredientName("Tomato");
        ingredient.setQuantity("200g");
        ingredient.setRecipe(r1);
        
        ingredient2 = new Ingredient();
        ingredient2.setIngredientName("Potato");
        ingredient2.setQuantity("100g");
        ingredient2.setRecipe(r1);
        
        ingredientRepository.save(ingredient);
        ingredientRepository.save(ingredient2);
	}
	
	
	@Test
	@DisplayName("Testing get all ingredients")
	public void testGetAllIng() {
		List<Ingredient> inglist = new ArrayList<>();
		inglist.add(ingredient);
		inglist.add(ingredient2);
		
		when(ingredientRepository.findAll()).thenReturn(inglist);
		
		List<Ingredient> fetchedlist = ingredientService.getAllIngredients();
		
		assertThat(fetchedlist).isNotNull();
		assertThat(fetchedlist.size()).isEqualTo(2);
		assertThat(fetchedlist.get(0).getIngredientName()).isEqualTo("Tomato");
		assertThat(fetchedlist.get(1).getIngredientName()).isEqualTo("Potato");
		verify(ingredientRepository, times(1)).findAll();
		
	}
}
