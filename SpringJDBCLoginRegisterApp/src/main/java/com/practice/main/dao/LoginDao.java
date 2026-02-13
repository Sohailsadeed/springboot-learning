package com.practice.main.dao;

import java.util.List;

import com.practice.main.beans.Student;

public interface LoginDao {
	public Student loginStudent(String email,String password);
	public int registerStudent(Student std);
}
