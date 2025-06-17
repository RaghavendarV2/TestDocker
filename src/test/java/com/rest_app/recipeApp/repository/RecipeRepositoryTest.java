package com.rest_app.recipeApp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.model.Recipe;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class RecipeRepositoryTest {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Recipe recipe1;
	private Category category;
	
	@BeforeEach
	public void testsetup() {
		category = new Category();
		category.setCategoryName("Chinese");
		categoryRepository.save(category);
		
		recipe1 = new Recipe();
		recipe1.setRecipeName("noodles");
		recipe1.setDescription("tasty");
		recipe1.setCookingTime(5);
		recipe1.setCategory(category);
	}
	
	@Test
	@DisplayName("Testing save and find by id")
	public void testSaveAndFindbyid() {
		//arrange - handled by before each
		
		//act
		Recipe savedRecipe = recipeRepository.save(recipe1);
		Optional<Recipe> fetched = recipeRepository.findById(savedRecipe.getRecipeId());
		
		//assert
		assertThat(fetched).isPresent();
		assertThat(fetched.get().getRecipeName()).isEqualTo("noodles");
	}
	
	@Test
	@DisplayName("Testing find by category")
	public void testFindByCategory() {
		//arrange
		recipeRepository.save(recipe1);
		
		//act
		List<Recipe> fetchedlist = recipeRepository.findByCategoryCategoryName("Chinese");
		
		//assert
		assertThat(fetchedlist.get(0).getRecipeName()).isEqualTo("noodles");
	}
	
}
