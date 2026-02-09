package com.practice.main.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.practice.main.beans.Student;
import com.practice.main.mappers.StudentMapper;

@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired
	JdbcTemplate template;
	@Override
	public List<Student> loginStudent(String email, String password) {
		String query="select * from student where email=? and password=?";
		List<Student> studentList= template.query(query, new StudentMapper(), new Object[] {email,password});
		
		return studentList;
	}

}
