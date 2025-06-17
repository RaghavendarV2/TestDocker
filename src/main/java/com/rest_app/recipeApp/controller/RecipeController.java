package com.rest_app.recipeApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.model.Recipe;
import com.rest_app.recipeApp.service.CategoryService;
import com.rest_app.recipeApp.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/show/greet")
    public ResponseEntity<String> showGreet(){
    	return new ResponseEntity<String>("Hello, this is rest from recipes!",HttpStatus.OK);
    }
    

    // 1. Create a new Recipe
    @PostMapping("/rights/create")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe created = recipeService.createRecipe(recipe);
        return ResponseEntity.ok(created);
    }

    // 2. Get Recipe by ID
    @GetMapping("/show/getById/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    // 3. Update Recipe
    @PutMapping("/rights/update/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe updated) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, updated);
        return ResponseEntity.ok(updatedRecipe);
    }

    // 4. Delete Recipe
    @DeleteMapping("/rights/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    // 5. Get Recipes by Category Name
    @GetMapping("/show/category/{categoryName}")
    public ResponseEntity<List<Recipe>> getRecipesByCategoryName(@PathVariable String categoryName) {
        List<Recipe> recipes = recipeService.getRecipesByCategoryName(categoryName);
        return ResponseEntity.ok(recipes);
    }

    // 6. Create a new Category
    @PostMapping("/rights/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category created = categoryService.createCategory(category);
        return ResponseEntity.ok(created);
    }
    
    // 7. Delete a category
    @DeleteMapping("/rights/delcat/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
    	categoryService.deleteCategoryById(id);
    	return new ResponseEntity<String>("Deleted the category",HttpStatus.OK);
    }
    
    // 8. show all categories
    @GetMapping("/show/catlist")
    public ResponseEntity<List<Category>> showAllCategories(){
    	List<Category> catlist = categoryService.getAllCategories();
    	return ResponseEntity.ok(catlist);
    }
}
