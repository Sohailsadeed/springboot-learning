package com.security.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
	@GetMapping("/greet")
	public String greet(HttpServletRequest request) {
		return "Hello, people of India, welcome to the world of Spring Security!" + " Jsession ID: " + request.getSession().getId();
	}
}
