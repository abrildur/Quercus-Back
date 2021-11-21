package com.quercus.PineappleSupermarket.services;


import com.quercus.PineappleSupermarket.models.ERole;
import com.quercus.PineappleSupermarket.models.Role;
import com.quercus.PineappleSupermarket.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleService {
	@Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findByName(ERole name){
        System.out.println(roleRepository.findAll());
        return roleRepository.findByName(name);
    }
}
