package com.quercus.PineappleSupermarket.services.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.quercus.PineappleSupermarket.dto.CategoryDTO;
import com.quercus.PineappleSupermarket.dto.ProductDTO;
import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;

public class MapperUtil {

	private static ModelMapper modelMapper = new ModelMapper();
	
	private MapperUtil() {
		
	}

	public static CategoryDTO convertToDto(Category category) {
		CategoryDTO catDto = modelMapper.map(category, CategoryDTO.class);
		
		catDto.setId(category.getId());
		catDto.setName(category.getName());
		catDto.setProducts(category.getProducts().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList()));
		
		return catDto;
	}
	
	public static ProductDTO convertToDto(Product product) {
		ProductDTO prodDto = modelMapper.map(product, ProductDTO.class);
		
		prodDto.setId(product.getId());
		prodDto.setName(product.getName());
		prodDto.setCategory(product.getCategory().getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setQuantity(product.getQuantity());
		prodDto.setPrice(product.getPrice());
		prodDto.setPicture(product.getPicture());
		
		return prodDto;
	}
	
	public static Category convertToDto(CategoryDTO categoryDTO) {
		Category cat = modelMapper.map(categoryDTO, Category.class);
		
		cat.setName(categoryDTO.getName());
		
		return cat;
	}
	
	public static Product convertToEntity(ProductDTO productDTO, CategoryDTO cat) {
		Product prod = modelMapper.map(productDTO, Product.class);
		
		prod.setCategory(MapperUtil.convertToDto(cat));
		
		return prod;
	}
}
