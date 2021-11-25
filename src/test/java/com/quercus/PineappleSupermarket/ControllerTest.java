package com.quercus.PineappleSupermarket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quercus.PineappleSupermarket.controllers.CategoryController;

@SpringBootTest(classes = ControllerTest.class)
class ControllerTest {

	@Autowired
	private CategoryController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
