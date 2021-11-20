package com.quercus.PineappleSupermarket.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.dto.CategoryDTO;
import com.quercus.PineappleSupermarket.dto.ProductDTO;
import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;
import com.quercus.PineappleSupermarket.services.ICategoryService;
import com.quercus.PineappleSupermarket.services.IProductService;
import com.quercus.PineappleSupermarket.services.mapper.MapperUtil;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/list")
	public List<Category> listCategories() {
		return categoryService.findAll();
	}
	
	@GetMapping(value = "/list/{id}")
	public List<ProductDTO> listProductsByCategoryId(@PathVariable Long id) {
		Optional<Category> cat = categoryService.findById(id);
		CategoryDTO catDto;
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		if(cat.isPresent()) {
			catDto = MapperUtil.convertToDto(cat.get());
			list = catDto.getProducts();
		}
		
		return list;
	}
	
	@PostMapping(value = "/save/{name}")
	public Category saveCategory(@PathVariable String name) {
		return categoryService.saveCategory(new Category(name));
	}
	
}
