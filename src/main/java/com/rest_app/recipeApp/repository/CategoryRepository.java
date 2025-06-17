package com.rest_app.recipeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest_app.recipeApp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
