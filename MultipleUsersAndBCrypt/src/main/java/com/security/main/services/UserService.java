package com.security.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.main.beans.Users;
import com.security.main.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;//since bcrypt is present in the application context, it will be injected here
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTService JwtService;
	
	public Users registerUser(Users user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}	

	public String verify(Users user) {
		
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
						);
		System.out.println(authenticationManager.getClass());//ProviderManager will be printed since it is the default implementation of AuthenticationManager
		
		return authentication.isAuthenticated() ? JwtService.generateToken(user.getUsername()) : "Inavlid User";
	}
}
