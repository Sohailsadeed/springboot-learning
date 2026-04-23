package com.security.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.main.beans.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	private List<Student> students = new ArrayList<>(List.of(
			new Student() {{ setId(1); setName("John"); setMarks(85); }},
			new Student() {{ setId(2); setName("Jane"); setMarks(92); }},
			new Student() {{ setId(3); setName("Bob"); setMarks(78); }}
			)) ;
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return students;
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
	    return (CsrfToken)request.getAttribute(CsrfToken.class.getName());
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}
}
