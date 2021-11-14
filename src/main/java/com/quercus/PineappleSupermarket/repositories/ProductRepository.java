package com.quercus.PineappleSupermarket.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quercus.PineappleSupermarket.enums.ERole;
import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;
import com.quercus.PineappleSupermarket.models.Role;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByName(String name);
	
	Optional<Product> findByPrice(float price);
	
	Optional<Product> findByQuantity(int quantity);
	
	List<Product> findByCategory_id(Long id);
	
	// Ver como hacer el de excel
}
