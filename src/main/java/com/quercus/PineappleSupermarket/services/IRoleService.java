package com.quercus.PineappleSupermarket.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.enums.ERole;
import com.quercus.PineappleSupermarket.models.Role;

@Service
public interface IRoleService {
	
	public Optional<Role> findByName(ERole name);
}
