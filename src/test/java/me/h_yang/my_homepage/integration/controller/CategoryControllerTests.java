package me.h_yang.my_homepage.integration.controller;

import me.h_yang.my_homepage.dto.CategoryDTO;
import me.h_yang.my_homepage.dto.SubCategoryDTO;
import me.h_yang.my_homepage.entity.Category;
import me.h_yang.my_homepage.entity.SubCategory;
import me.h_yang.my_homepage.repository.CategoryRepository;
import me.h_yang.my_homepage.repository.SubCategoryRepository;
import me.h_yang.my_homepage.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;


	List<Category> categories;

	@BeforeEach
	void setUp() {
		categoryRepository.deleteAll();
		subCategoryRepository.deleteAll();
		categoryRepository.saveAll(List.of(
				new Category("Category1"),
				new Category("Category2"),
				new Category("Category3"),
				new Category("Category4")
		));

		categories = categoryRepository.findAll();

		subCategoryRepository.saveAll(List.of(
				new SubCategory(categories.get(0), "SubCategory1"),
				new SubCategory(categories.get(0), "SubCategory2"),
				new SubCategory(categories.get(0), "SubCategory3"),
				new SubCategory(categories.get(1), "SubCategory4"),
				new SubCategory(categories.get(1), "SubCategory5"),
				new SubCategory(categories.get(3), "SubCategory6"),
				new SubCategory(categories.get(3), "SubCategory7"),
				new SubCategory(categories.get(3), "SubCategory8"),
				new SubCategory(categories.get(3), "SubCategory9"),
				new SubCategory(categories.get(3), "SubCategory10")
		));



	}

	@Test
	void getCategory() {



		ResponseEntity<List<CategoryDTO>> categories = testRestTemplate.exchange(
				"/open/categories",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CategoryDTO>>(){});

		Assertions.assertEquals(HttpStatus.OK, categories.getStatusCode());

		List<CategoryDTO> categoryList = categories.getBody();
        Assertions.assertNotNull(categoryList);
		Assertions.assertInstanceOf(CategoryDTO.class, categoryList.getFirst());
        Assertions.assertEquals(4, categoryList.size());

	}


	@Test
	void getSubCategory() {

		ResponseEntity<List<SubCategoryDTO>> subCategories = testRestTemplate.exchange(
				"/open/categories/subcategories?categoryId=4",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<SubCategoryDTO>>(){});

		Assertions.assertEquals(HttpStatus.OK, subCategories.getStatusCode());

		List<SubCategoryDTO> subCategoryList = subCategories.getBody();
		Assertions.assertNotNull(subCategoryList);
        Assertions.assertInstanceOf(SubCategoryDTO.class, subCategoryList.getFirst());
		Assertions.assertEquals(5, subCategoryList.size());
	}
}
