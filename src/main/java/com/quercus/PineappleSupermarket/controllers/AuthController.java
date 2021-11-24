package com.quercus.PineappleSupermarket.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.models.ERole;
import com.quercus.PineappleSupermarket.models.Role;
import com.quercus.PineappleSupermarket.models.User;
import com.quercus.PineappleSupermarket.payload.response.JwtResponse;
import com.quercus.PineappleSupermarket.payload.resquest.LoginRequest;
import com.quercus.PineappleSupermarket.payload.resquest.SignupRequest;
import com.quercus.PineappleSupermarket.services.UserService;
import com.quercus.PineappleSupermarket.services.impl.UserDetailsImpl;
import com.quercus.PineappleSupermarket.services.RoleService;
import com.quercus.PineappleSupermarket.security.Jwt.JwtUtils;


@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));

    }



	
}
