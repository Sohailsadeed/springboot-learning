package com.practice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String args[]) {
		System.out.println("SpringBoot Application Started");
		SpringApplication.run(Main.class, args);
		System.out.println("SpringBoot Application terminated");
	}
}
