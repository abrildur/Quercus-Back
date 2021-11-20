package com.quercus.PineappleSupermarket.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.dto.CategoryDTO;
import com.quercus.PineappleSupermarket.dto.ProductDTO;
import com.quercus.PineappleSupermarket.model.ProductModel;
import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;
import com.quercus.PineappleSupermarket.services.ICategoryService;
import com.quercus.PineappleSupermarket.services.IProductService;
import com.quercus.PineappleSupermarket.services.mapper.MapperUtil;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping(value = "/save")
	public ProductDTO saveProduct(@RequestBody ProductModel product) throws Exception {
		
		Optional<Category> cat;
		CategoryDTO catDto;
		ProductDTO prodDto = new ProductDTO();
		
		cat = categoryService.findByName(product.getCategory());
		
		if(cat.isEmpty()) {
			throw new Exception("Category not found");
		}
		
		catDto = MapperUtil.convertToDto(cat.get());
  		
		prodDto.setName(product.getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setQuantity(product.getQuantity());
		prodDto.setPrice(product.getPrice());
		prodDto.setPicture(product.getPicture());
		prodDto.setCategory(catDto.getName());
		
		return MapperUtil.convertToDto(productService.saveProduct(MapperUtil.convertToEntity(prodDto, catDto)));
	}
	
	@PostMapping(value = "/update")
	public ProductDTO updateProduct(@RequestBody ProductModel product) throws Exception {
		
		Optional<Category> cat;
		CategoryDTO catDto;
		ProductDTO prodDto = new ProductDTO();
		
		cat = categoryService.findByName(product.getCategory());
		
		if(cat.isEmpty()) {
			throw new Exception("Category not found");
		}
		
		catDto = MapperUtil.convertToDto(cat.get());
  		
		prodDto.setId(product.getId());
		prodDto.setName(product.getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setQuantity(product.getQuantity());
		prodDto.setPrice(product.getPrice());
		prodDto.setPicture(product.getPicture());
		prodDto.setCategory(catDto.getName());
		
		return MapperUtil.convertToDto(productService.saveProduct(MapperUtil.convertToEntity(prodDto, catDto)));
	}
	
	@GetMapping(value = "/list")
	public List<ProductDTO> get() {
		return MapperUtil.mapList(productService.findAll(), ProductDTO.class);
	}
}
