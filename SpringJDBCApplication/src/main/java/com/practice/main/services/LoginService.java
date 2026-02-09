package com.practice.main.services;

import java.util.List;

import com.practice.main.beans.Student;

public interface LoginService {
	public List<Student> loginService(String email,String password);
}
