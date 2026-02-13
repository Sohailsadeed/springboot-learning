package com.practice.main.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.practice.main.beans.Student;

public class StudentMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student std=new Student();
		std.setName(rs.getString("name"));
		std.setEmail(rs.getString("email"));
		std.setPassword(rs.getString("password"));
		std.setCity(rs.getString("city"));
		std.setGender(rs.getString("gender"));
		return std;
	}

}
