package com.security.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.main.beans.Users;
import com.security.main.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) {
		return userService.registerUser(user);
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody Users user) {
		return userService.verify(user);
	}
}
