package com.quercus.PineappleSupermarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.repositories.CategoryRepository;
import com.quercus.PineappleSupermarket.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Optional<Category> findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

}
