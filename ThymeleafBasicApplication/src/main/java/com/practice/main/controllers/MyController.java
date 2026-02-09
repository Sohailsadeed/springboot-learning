package com.practice.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("name", "Wizard");
		return "welcome";
	}
}
