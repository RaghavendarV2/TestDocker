package com.rest_app.recipeApp.repository;

import com.rest_app.recipeApp.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	@DisplayName("Testing save and find by ID")
	public void testSaveAndFindById() {
		//Arranging
		Category category = new Category();
		category.setCategoryName("British");
		
		//Act
		Category savedCategory = categoryRepository.save(category);
		Optional<Category> recievedCategory = categoryRepository.findById(savedCategory.getCategoryId());
		
		//Assert
		assertThat(recievedCategory.isPresent());
		assertThat(recievedCategory.get().getCategoryName()).isEqualTo("British");
	}
	
	@Test
	@DisplayName("Testing deleting")
	public void testDelete() {
		//arrange
		Category category = new Category();
		category.setCategoryName("Gowtham dishes");
		Category savedCategory = categoryRepository.save(category);
		
		//act
		categoryRepository.delete(savedCategory);
		Optional<Category> check = categoryRepository.findById(savedCategory.getCategoryId());
		
		//assert
		assertThat(check).isNotPresent();
		
	}
	
	@Test
	@DisplayName("Testing find All")
	public void testFindAll() {
		//arrange
		Category cat1 = new Category();
		Category cat2 = new Category();
		
		cat1.setCategoryName("lebanese");
		cat2.setCategoryName("jap");
		
		categoryRepository.save(cat1);
		categoryRepository.save(cat2);
		
		//act
		List<Category> catlist = categoryRepository.findAll();
		
		//assert
		assertEquals(2,catlist.size());
		
	}

}
