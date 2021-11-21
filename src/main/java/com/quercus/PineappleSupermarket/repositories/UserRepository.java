package com.quercus.PineappleSupermarket.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quercus.PineappleSupermarket.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    
    //Optional<User> findByEmail(String email);

    //Boolean existsByEmail(String email);
}
