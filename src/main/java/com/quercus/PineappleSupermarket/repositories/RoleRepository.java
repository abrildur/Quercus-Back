package com.quercus.PineappleSupermarket.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quercus.PineappleSupermarket.enums.ERole;
import com.quercus.PineappleSupermarket.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(ERole name);
}
