package com.example.demo.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.article.repository.AccountRepository;

@Controller
@RequestMapping(path = "/pc/account")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping(path = "/hello")
	public ModelAndView helloWorld() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/hello");
		return mv;
	}
}
