package com.rest_app.recipeApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rest_app.recipeApp.model.Category;
import com.rest_app.recipeApp.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
public class CategoryServiceTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;
	
	private Category test_cat;
	
	@BeforeEach
	public void testsetup() {
		test_cat = new Category();
		test_cat.setCategoryName("SampleCat");
	}
	
	@Test
	@DisplayName("Testing save of category service")
	public void testCategoryServiceSave() {
		
		when(categoryRepository.save(test_cat)).thenReturn(test_cat);
		
		Category saved_cat = categoryService.createCategory(test_cat);
		
		assertThat(saved_cat).isNotNull();
		assertThat(saved_cat.getCategoryName()).isEqualTo("SampleCat");
		verify(categoryRepository, times(1)).save(test_cat);
	}
	
	@Test
	@DisplayName("Testing get category by Id of category service")
	public void testGetCategoryById() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(test_cat));
		
		Category fetched = categoryService.getCategoryById(1L);
		
		assertThat(fetched).isNotNull();
		assertThat(fetched.getCategoryName()).isEqualTo("SampleCat");
		verify(categoryRepository, times(1)).findById(1L);
	}
	
	@Test
	@DisplayName("Testing get all category of category service")
	public void testGetAllCategory() {
		Category test_cat2 = new Category();
		test_cat2.setCategoryName("Sample2");
		
		List<Category> categories = new ArrayList<>();
		categories.add(test_cat);
		categories.add(test_cat2);
		
		when(categoryRepository.findAll()).thenReturn(categories);
		
		List<Category> fetchedlist = categoryService.getAllCategories();
		
		assertThat(fetchedlist).isNotNull();
		assertThat(fetchedlist.size()).isEqualTo(2);
		verify(categoryRepository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("Should delete a category by ID")
	void testRemoveCategory() {
		doNothing().when(categoryRepository).deleteById(1L);

	    categoryService.deleteCategoryById(1L);

	    verify(categoryRepository, times(1)).deleteById(1L);
	}

	
}
