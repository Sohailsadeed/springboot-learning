package com.practice.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.main.beans.Student;
import com.practice.main.services.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	
	@GetMapping("/")
	public String getHome() {
		return "homePage";
	}
	
	@GetMapping("/error")
	public String getError() {
		return "error";
	}
	
	@GetMapping("/aboutUs")
	public String getAbout() {
		return "aboutUs-page";
	}
	
	@GetMapping("/contactUs")
	public String getContact() {
		return "contactUs-page";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login-page";
	}
	
	@GetMapping("/register")
	public String getRegistry() {
		return "register-page";
	}
	
	@Autowired
	LoginService login;
	
	@GetMapping("/loginPage")
	public String loginStudent(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session) {
		String page="error";
		System.out.println(email);
		System.out.println(password);
		try {
			Student stud = login.loginService(email, password);
			if(stud!=null) {
				session.setAttribute("student", stud);
				page="profile-page";	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	@PostMapping("/registerPage")
	public String registerStudent(@ModelAttribute Student std,HttpSession session) {
		String page="error";
		boolean status=false;
		try {
			status=login.registerService(std);
			Student stud = login.loginService(std.getEmail(), std.getPassword());
			if(stud!=null) {
				session.setAttribute("student", stud);
				page="profile-page";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	@GetMapping("/logout")
	public String getLogOut(HttpSession session) {
		session.invalidate();
		return "homePage";
	}
}
