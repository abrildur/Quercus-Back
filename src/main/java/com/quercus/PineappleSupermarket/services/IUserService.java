package com.quercus.PineappleSupermarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.User;

@Service
public interface IUserService {
	
	public List<User> findAll();
	
	public Optional<User> findById(Long id);
	
	public User saveUser(User user);
	
	public void deleteUser(Long id);
	
	public Optional<User> findByUsername(String username);
    
	public Optional<User> findByEmail(String email);

}
