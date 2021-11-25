package com.quercus.PineappleSupermarket.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.services.RoleService;
import com.quercus.PineappleSupermarket.models.ERole;
import com.quercus.PineappleSupermarket.models.Role;
import com.quercus.PineappleSupermarket.models.User;
import com.quercus.PineappleSupermarket.services.UserService;
import com.quercus.PineappleSupermarket.payload.resquest.UserRequest;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RoleService roleService; 
	
	@Autowired
	PasswordEncoder encoder; 

	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){
		if(userService.existsByUsername(userRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body("Error: This user already exists!");
		}
		
		User user = new User(userRequest.getUsername(),
				encoder.encode(userRequest.getPassword()), 
				userRequest.getEmail(),
				userRequest.getName(),
				userRequest.getLastName()
				);
		Set<String> strRoles = userRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		System.out.println("steRoless"+strRoles);
		System.out.println("rolesss"+roles);
		System.out.println("newusersss"+user);
		
		  if (strRoles == null) {
	            Role userRole = roleService.findByName(ERole.ROLE_VIEWER)
	                    .orElseThrow(() -> new RuntimeException("Error: There is no such Role."));
	            roles.add(userRole);
	        } else {
	            strRoles.forEach(role -> {
	                switch (role) {
	                    case "admin":
	                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
	                                .orElseThrow(() -> new RuntimeException("Error: There is no such Role."));
	                        roles.add(adminRole);
	                        break;
	                    default:
	                        Role userRole = roleService.findByName(ERole.ROLE_VIEWER)
	                                .orElseThrow(() -> new RuntimeException("Error: There is no such Role."));
	                        roles.add(userRole);
	                }
	            });
	        }
		  
		  user.setRoles(roles);
		  userService.save(user);
		  
		  return ResponseEntity.ok("Registered user!");
		
	}
	
	
}
