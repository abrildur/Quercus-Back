package com.quercus.PineappleSupermarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.Product;

@Service
public interface IProductService {
	
	public List<Product> findAll();
	
	public Product saveProduct(Product product);
	
	public Optional<Product> findById(Long id);
	
	public void deleteProduct(Long id);
	
	public Optional<Product> findByName(String name);
	
	public Optional<Product> findByPrice(float price);
	
	public Optional<Product> findByQuantity(int quantity);
	
	public List<Product> findByCategory_id(Long id);
}
