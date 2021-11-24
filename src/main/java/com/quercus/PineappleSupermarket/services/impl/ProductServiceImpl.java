package com.quercus.PineappleSupermarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;
import com.quercus.PineappleSupermarket.repositories.CategoryRepository;
import com.quercus.PineappleSupermarket.repositories.ProductRepository;
import com.quercus.PineappleSupermarket.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@Override
	public Product saveProduct(Product product) {
		
		Product prod = productRepository.save(product);
		Category cad = prod.getCategory();
		prod.setCategory(cad);
		
		cad.addProducts(prod);
		
		return productRepository.save(prod);
	}
	
	@Override
	public void deleteProduct(Long id) {
		Optional<Product> prod = productRepository.findById(id);
		Optional<Category> cat;
		
		if(prod.isPresent()) {
			cat = categoryRepository.findById(prod.get().getCategory().getId());
			
			if(cat.isPresent()) {
				cat.get().deleteProduct(prod.get());
			}
		}
		
		productRepository.deleteById(id);		
	}
	
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
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

	@Override
	public List<Product> findByNameContaining(String infix) {
		return productRepository.findByNameContaining(infix);
	}

	@Override
	public List<Product> findAllByOrderByNameAsc() {
		return productRepository.findAllByOrderByNameAsc();
	}

	@Override
	public List<Product> findAllByOrderByNameDesc() {
		return productRepository.findAllByOrderByNameDesc();
	}

	@Override
	public List<Product> findAllByOrderByPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	@Override
	public List<Product> findAllByOrderByPriceDesc() {
		return productRepository.findAllByOrderByPriceDesc();
	}

	@Override
	public List<Product> findAllByOrderByQuantityAsc() {
		return productRepository.findAllByOrderByQuantityAsc();
	}

	@Override
	public List<Product> findAllByOrderByQuantityDesc() {
		return productRepository.findAllByOrderByQuantityDesc();
	}
}
