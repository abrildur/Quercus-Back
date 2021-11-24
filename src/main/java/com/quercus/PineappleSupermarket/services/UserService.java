package com.quercus.PineappleSupermarket.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.models.User;
import com.quercus.PineappleSupermarket.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public void save(User user){
    	userRepository.saveAndFlush(user);
    }
    public void saveUsers(Set<User> users){
    	userRepository.saveAll(users);
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){ return userRepository.findAll();}
	
}
