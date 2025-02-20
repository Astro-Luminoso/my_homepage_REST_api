package me.h_yang.my_homepage.integration.controller;

import me.h_yang.my_homepage.dto.CategoryDTO;
import me.h_yang.my_homepage.dto.SubCategoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
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
        Assertions.assertEquals(4, categoryList.size());
	}


	@ParameterizedTest
	@CsvSource({
			"'1', 3",
			"'2', 5",
			"'4', 5"
	})
	void getSubCategoryOfPhotography(String categoryId, int expectedSize) {
		ResponseEntity<List<SubCategoryDTO>> subCategories = testRestTemplate.exchange(
				"/open/categories/subcategories?categoryId=" + categoryId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<SubCategoryDTO>>(){});

		Assertions.assertEquals(HttpStatus.OK, subCategories.getStatusCode());

		List<SubCategoryDTO> subCategoryList = subCategories.getBody();
		Assertions.assertNotNull(subCategoryList);
		Assertions.assertEquals(expectedSize, subCategoryList.size());
	}
}
