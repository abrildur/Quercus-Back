package com.quercus.PineappleSupermarket.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quercus.PineappleSupermarket.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByName(String name);
	
	Optional<Product> findByPrice(float price);
	
	Optional<Product> findByQuantity(int quantity);
	
	List<Product> findByCategory_id(Long id);
	
	List<Product> findByNameContaining(String infix);
	
	List<Product> findAllByOrderByNameAsc();
	
	List<Product> findAllByOrderByNameDesc();
	
	List<Product> findAllByOrderByPriceAsc();
	
	List<Product> findAllByOrderByPriceDesc();
	
	List<Product> findAllByOrderByQuantityAsc();
	
	List<Product> findAllByOrderByQuantityDesc();
}
