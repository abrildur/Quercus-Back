package com.quercus.PineappleSupermarket;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.quercus.PineappleSupermarket.enums.ERole;
import com.quercus.PineappleSupermarket.models.Role;
import com.quercus.PineappleSupermarket.repositories.RoleRepository;

@SpringBootApplication
public class PineappleSupermarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PineappleSupermarketApplication.class, args);
	}

	@Component
	public class PostConstructExampleBean {

		@Autowired
		private RoleRepository roleRepository;

		@PostConstruct
		public void init() {
			Role role = new Role();
	
			if(roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
				role.setName(ERole.ROLE_ADMIN);
				roleRepository.save(role);
			}
			
			role = new Role();
			if(roleRepository.findByName(ERole.ROLE_VIEWER).isEmpty()) {
				role.setName(ERole.ROLE_VIEWER);
				roleRepository.save(role);
			}
		}
	}
}
