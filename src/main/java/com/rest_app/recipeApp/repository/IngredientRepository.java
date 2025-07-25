package com.rest_app.recipeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest_app.recipeApp.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
