package com.security.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.main.beans.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	public Users findByUsername(String username);
}
