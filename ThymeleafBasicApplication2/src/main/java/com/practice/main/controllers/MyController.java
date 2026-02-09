package com.practice.main.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/openProfilePage")
	public String getIndex(Model model) {
		model.addAttribute("name", "Wizard");
		return "welcome";
	}
	
	@GetMapping("/openConditionalPage")
	public String openConditionalPage(Model model) {
		int number1=10;
		int number2=12;
		
		model.addAttribute("number1", number1);
		model.addAttribute("number2", number2);
		
		return "conditionalPage";
	}
	
	@GetMapping("/openIterativePage")
	public String openIterative(Model model) {
		List<Integer> list= List.of(10,20,30,40,50);
		model.addAttribute("numericList", list);
		return "iterativePage";
	}
}
