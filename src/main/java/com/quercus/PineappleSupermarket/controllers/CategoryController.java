package com.quercus.PineappleSupermarket.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.dto.CategoryDTO;
import com.quercus.PineappleSupermarket.dto.ProductDTO;
import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.services.ICategoryService;
import com.quercus.PineappleSupermarket.services.mapper.MapperUtil;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<CategoryDTO>> listCategories() {
		return new ResponseEntity<List<CategoryDTO>>(categoryService.findAll().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList()),
													HttpStatus.OK);
	}
	
	@GetMapping(value = "/list/{id}")
	public ResponseEntity<List<ProductDTO>> listProductsByCategoryId(@PathVariable Long id) {
		Optional<Category> cat = categoryService.findById(id);
		CategoryDTO catDto;
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		if(cat.isPresent()) {
			catDto = MapperUtil.convertToDto(cat.get());
			list = catDto.getProducts();
		}
		
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping(value = "/save/{name}")
	public ResponseEntity<CategoryDTO> saveCategory(@PathVariable String name) {
		Category cat = categoryService.saveCategory(new Category(name));
		return new ResponseEntity<CategoryDTO>(MapperUtil.convertToDto(cat), HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,
								 @RequestParam String name) throws Exception {
		
		Optional<Category> cat = categoryService.findById(id);
		
		if(cat.isEmpty()) {
			throw new NotFoundException("La categoría no existe");
		}
		
		cat.get().setName(name);
		
		return new ResponseEntity<CategoryDTO>(MapperUtil.convertToDto(categoryService.saveCategory(cat.get())), 
												HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws Exception {
		categoryService.deleteCategory(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
