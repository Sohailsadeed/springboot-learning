package com.practice.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.main.beans.Student;
import com.practice.main.services.LoginService;

@Controller
public class MyController {
	
	@GetMapping("/loginPage")
	public String getLoginForm() {
		return "login";
	}
	
	@Autowired
	LoginService login;
	
	@PostMapping("/login")
	public String loginStudent(@RequestParam("email") String email, @RequestParam("password") String password,Model model) {
		String page="error";
		try {
			List<Student> studList = login.loginService(email, password);
			if(studList.size()!=0) {
				model.addAttribute("student",studList.get(0));
				page="profile";	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
