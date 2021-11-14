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
			Role adminRole = new Role();
			adminRole.setName(ERole.ROLE_ADMIN);
			roleRepository.save(adminRole);

			Role userRole = new Role();
			userRole.setName(ERole.ROLE_VIEWER);
			roleRepository.save(userRole);
		}
	}
}
