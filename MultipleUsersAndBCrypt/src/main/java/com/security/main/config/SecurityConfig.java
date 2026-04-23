package com.security.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.main.filters.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securrityFilterChain(HttpSecurity http) throws Exception {
		http
		     .csrf(customizer-> customizer.disable())
		     .authorizeHttpRequests(auth-> auth
		    		 					.requestMatchers(HttpMethod.POST,"/users/register","/users/login")
		    		 					.permitAll()
		    		 					.anyRequest().authenticated())
		     .formLogin(formLogin-> formLogin.disable())	
			 .httpBasic(basic-> basic.disable())
			 .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public AuthenticationProvider authProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) { 
		// Spring will automatically create the bean of DaoAuthenticationProvider and inject
        // the required dependencies(encoder and userdetailsservice) when we define Passwordencoder and UserDetailsService in the ApplicationContext, 
		//so we can skip this step only if we are just setting passwordencoder and userDetailsService,
		//but if we want to set any other property of DaoAuthenticationProvider then we need to define this bean explicitly.
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();//returns ProviderManager since it is the default implementation of AuthenticationManager
	}
}
