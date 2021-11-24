package com.quercus.PineappleSupermarket.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.quercus.PineappleSupermarket.models.User;
import com.quercus.PineappleSupermarket.payload.resquest.UserRequest;

public interface IUserService {
	
	ResponseEntity<User> crateUser(UserRequest newUser);
	ResponseEntity<List<User>>listUser();
	ResponseEntity<User> findUser(int id);
	ResponseEntity<User> updateUser(int id, UserRequest newUser);
	ResponseEntity<HttpStatus> deleteUser(int id); 
	
	
	

}
