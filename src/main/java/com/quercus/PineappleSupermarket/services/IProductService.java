package com.quercus.PineappleSupermarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.Product;

@Service
public interface IProductService {
	
	public Product saveProduct(Product product);
	
	public void deleteProduct(Long id);
	
	public List<Product> findAll();
	
	public Optional<Product> findById(Long id);
	
	public Optional<Product> findByName(String name);
	
	public Optional<Product> findByPrice(float price);
	
	public Optional<Product> findByQuantity(int quantity);
	
	public List<Product> findByCategory_id(Long id);
	
	public List<Product> findByNameContaining(String infix);
	
	public List<Product> findAllByOrderByNameAsc();
	
	public List<Product> findAllByOrderByNameDesc();
	
	public List<Product> findAllByOrderByPriceAsc();
	
	public List<Product> findAllByOrderByPriceDesc();
	
	public List<Product> findAllByOrderByQuantityAsc();
	
	public List<Product> findAllByOrderByQuantityDesc();
}
