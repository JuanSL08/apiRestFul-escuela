package com.tismart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EscuelasApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(EscuelasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		String password = "admin";
		String passwordBcrypt = passwordEncoder.encode(password);
		System.out.println(passwordBcrypt);
		password = "super";
		passwordBcrypt = passwordEncoder.encode(password);
		System.out.println(passwordBcrypt);
		password = "user";
		passwordBcrypt = passwordEncoder.encode(password);
		System.out.println(passwordBcrypt);
		*/
	}
	
	
}
