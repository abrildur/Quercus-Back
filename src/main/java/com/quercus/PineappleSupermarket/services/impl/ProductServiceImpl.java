package com.quercus.PineappleSupermarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;
import com.quercus.PineappleSupermarket.repositories.CategoryRepository;
import com.quercus.PineappleSupermarket.repositories.ProductRepository;
import com.quercus.PineappleSupermarket.services.ICategoryService;
import com.quercus.PineappleSupermarket.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		
		Product prod = productRepository.save(product);
		Category cad = prod.getCategory();
		prod.setCategory(cad);
		
		cad.addProducts(prod);
		
		return productRepository.save(prod);
	}
	
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);		
	}

	@Override
	public Optional<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public Optional<Product> findByPrice(float price) {
		return productRepository.findByPrice(price);
	}

	@Override
	public Optional<Product> findByQuantity(int quantity) {
		return productRepository.findByQuantity(quantity);
	}

	@Override
	public List<Product> findByCategory_id(Long id) {
		return productRepository.findByCategory_id(id);
	}

}
