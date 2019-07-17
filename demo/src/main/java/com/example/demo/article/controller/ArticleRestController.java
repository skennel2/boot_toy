package com.example.demo.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.article.domain.Article;
import com.example.demo.article.domain.exception.ArticleNotFoundException;
import com.example.demo.article.repository.ArticleRepository;

@RestController
@RequestMapping(path = "/article")
public class ArticleRestController {
	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Article> getById(@PathVariable Long id) {
		return articleRepository.findById(id)
				.map(article -> ResponseEntity.ok(article))
				.orElseThrow(() -> new ArticleNotFoundException());
	}
}
