package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {	
		return userService.getUserList();
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}
}
