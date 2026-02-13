package com.practice.main.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.practice.main.beans.Student;
import com.practice.main.mappers.StudentMapper;

@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired
	JdbcTemplate template;
	@Override
	public Student loginStudent(String email, String password) {
		String query="select * from student where email=? and password=?";
		Student std=template.queryForObject(query, new BeanPropertyRowMapper<>(Student.class),email,password);
		return std;
	}
	@Override
	public int registerStudent(Student std) {
		String query="insert into student(name,email,password,gender,city) values(?,?,?,?,?)";
		return template.update(query,std.getName(),std.getEmail(),std.getPassword(),std.getGender(),std.getCity());
	}

}
