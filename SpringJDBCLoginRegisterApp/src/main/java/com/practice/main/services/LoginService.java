package com.practice.main.services;

import com.practice.main.beans.Student;

public interface LoginService {
	public Student loginService(String email,String password);
	public boolean registerService(Student std);
}
