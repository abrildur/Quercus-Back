package com.quercus.PineappleSupermarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.services.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	
	@PostMapping(value = "/save")
	public Category saveCategory(@RequestBody Category category) {
		return categoryService.save(category);
	}
}
