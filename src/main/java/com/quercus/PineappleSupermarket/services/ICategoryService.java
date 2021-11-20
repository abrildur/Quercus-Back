package com.quercus.PineappleSupermarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.Category;

@Service
public interface ICategoryService {
	
	public List<Category> findAll();
	
	public Optional<Category> findById(Long id);
	
	public Optional<Category> findByName(String name);
	
	public Category saveCategory(Category category);
}
