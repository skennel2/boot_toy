package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.article.domain.Account;
import com.example.demo.article.repository.AccountRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private AccountRepository accountRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		accountRepository.deleteAll();
		accountRepository.save(new Account("skennel", "1234"));
	}
}
