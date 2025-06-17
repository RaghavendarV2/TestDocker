package com.rest_app.recipeApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.model.Recipe;
import com.rest_app.recipeApp.repository.CategoryRepository;
import com.rest_app.recipeApp.repository.RecipeRepository;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    
    @Mock
    private CategoryRepository categoryRepository;
    
    @InjectMocks
    private RecipeService recipeService;
    
    private Recipe recipe1;
    private Category category1;
    
    @BeforeEach
    public void setUp() {
        category1 = new Category();
        category1.setCategoryId(1L);
        category1.setCategoryName("Tasty");
        
        recipe1 = new Recipe();
        recipe1.setRecipeName("Potato Sides");
        recipe1.setDescription("Good");
        recipe1.setCookingTime(10);
        recipe1.setCategory(category1);
    }
    
    @Test
    @DisplayName("Should create and save a recipe successfully")
    public void testCreateRecipe() {
        // Arrange: Stub the categoryRepository to return the category when searched by ID
        when(categoryRepository.findById(category1.getCategoryId()))
                .thenReturn(Optional.of(category1));
        
        // Arrange: Stub the recipeRepository to return the recipe when saving
        when(recipeRepository.save(recipe1)).thenReturn(recipe1);
        
        // Act: Call the service method
        Recipe savedRecipe = recipeService.createRecipe(recipe1);
        
        // Assert: Verify that the returned recipe is not null and properties match
        assertThat(savedRecipe).isNotNull();
        assertThat(savedRecipe.getRecipeName()).isEqualTo("Potato Sides");
        assertThat(savedRecipe.getCategory()).isEqualTo(category1);
        
        // Verify that the repository methods were invoked exactly once
        verify(categoryRepository, times(1)).findById(category1.getCategoryId());
        verify(recipeRepository, times(1)).save(recipe1);
    }
    
    @Test
    @DisplayName("Should find a recipe successfully")
    public void testFindByIdRecipe() {
    	when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe1));
    	
    	Recipe fetched = recipeService.getRecipeById(1L);
    	
    	assertThat(fetched).isNotNull();
    	assertThat(fetched.getRecipeName()).isEqualTo("Potato Sides");
    	verify(recipeRepository, times(1)).findById(1L);
    }
    
    @Test
    @DisplayName("Should delete a recipe successfully")
    public void testDeleteRecipe() {
    	doNothing().when(recipeRepository).deleteById(1L);
    	
    	recipeService.deleteRecipe(1L);
    	
    	verify(recipeRepository, times(1)).deleteById(1L);
    }
}
