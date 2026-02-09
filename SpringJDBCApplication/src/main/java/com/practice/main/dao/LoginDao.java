package com.practice.main.dao;

import java.util.List;

import com.practice.main.beans.Student;

public interface LoginDao {
	public List<Student> loginStudent(String email,String password);
}
