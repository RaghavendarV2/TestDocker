package com.rest_app.recipeApp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.model.Ingredient;
import com.rest_app.recipeApp.model.Recipe;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IngredientRepositoryTest {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//testing get all ingredients
	
	@Test
	@DisplayName("Testing get all ingredients")
	public void testallingredients() {
		//arrange
		Ingredient ig1 = new Ingredient();
		Ingredient ig2 = new Ingredient();
		
		Category c1 = new Category();
		c1.setCategoryName("Sample cat");
		categoryRepository.save(c1);
		
		Recipe r1 = new Recipe();
		r1.setRecipeName("testrecipe");
		r1.setDescription("test recipe description");
		r1.setCookingTime(20);
		r1.setCategory(c1);
		recipeRepository.save(r1);
		
		
		
		ig1.setIngredientName("Chillies");
		ig1.setQuantity("10g");
		ig1.setRecipe(r1);
		
		ig2.setIngredientName("Cheese");
		ig2.setQuantity("20g");
		ig2.setRecipe(r1);
		
		ingredientRepository.save(ig1);
		ingredientRepository.save(ig2);
		
		//act
		List<Ingredient> inglist = ingredientRepository.findAll();
		System.out.println("Ingredient list size: " + inglist.size());
		
		//assert
		assertEquals(2, inglist.size());
		
	}
	
}
