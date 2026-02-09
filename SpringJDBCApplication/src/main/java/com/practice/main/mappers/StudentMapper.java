package com.practice.main.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.practice.main.beans.Student;

public class StudentMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student std=new Student();
		std.setName(rs.getString(1));
		std.setEmail(rs.getString(2));
		std.setPassword(rs.getString(3));
		std.setCity(rs.getString(4));
		return std;
	}

}
