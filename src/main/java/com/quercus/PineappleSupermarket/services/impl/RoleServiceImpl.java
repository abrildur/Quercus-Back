package com.quercus.PineappleSupermarket.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quercus.PineappleSupermarket.enums.ERole;
import com.quercus.PineappleSupermarket.models.Role;
import com.quercus.PineappleSupermarket.repositories.RoleRepository;
import com.quercus.PineappleSupermarket.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findByName(ERole name) {
		return roleRepository.findByName(name);
	}

}
