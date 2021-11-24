package com.quercus.PineappleSupermarket;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import com.quercus.PineappleSupermarket.models.ERole;
import com.quercus.PineappleSupermarket.models.Role;
import com.quercus.PineappleSupermarket.models.User;
import com.quercus.PineappleSupermarket.repositories.RoleRepository;
import com.quercus.PineappleSupermarket.repositories.UserRepository;

@SpringBootApplication
public class PineappleSupermarketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PineappleSupermarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FileSystemUtils.deleteRecursively(Paths.get("uploads").toFile());
		Files.createDirectory(Paths.get("uploads"));
	}
	
	@Component
	public class PostConstructExampleBean {

		@Autowired
		private RoleRepository roleRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		PasswordEncoder encoder; 

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
			
			User user  = new User();
			Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
			Set<Role> roles = Stream.of(userRole.get())
                    .collect(Collectors.toCollection(HashSet<Role>::new));

			
			if(userRepository.findByUsername("john").isEmpty()) {
				user.setUsername("john");
				user.setPassword(encoder.encode("123456"));
				user.setEmail("john@pineapple.com");
				user.setName("John");
				user.setLastName("Smith");
				user.setRoles(roles);
				
				userRepository.save(user);
			}
			
			 user  = new User();
			 userRole = roleRepository.findByName(ERole.ROLE_VIEWER);
			 roles = Stream.of(userRole.get())
                    .collect(Collectors.toCollection(HashSet<Role>::new));

			
			if(userRepository.findByUsername("genericUser").isEmpty()) {
				user.setUsername("genericUser");
				user.setPassword(encoder.encode("1234567"));
				user.setEmail("genericUser@pineapple.com");
				user.setName("User");
				user.setLastName("Viewer");
				user.setRoles(roles);
				
				userRepository.save(user);
			}
			
		}
		
	}
}
