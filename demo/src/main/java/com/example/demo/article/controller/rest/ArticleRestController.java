package com.example.demo.article.controller.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.service.ArticleService;
@RestController
@RequestMapping(path = "/api/article")
public class ArticleRestController {

	@Autowired
	private ArticleService articleService; 
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Resource<ArticleView>> getById(@PathVariable Long id) {
		ArticleView article = articleService.getById(id);
		
		Resource<ArticleView> articleResource = new Resource<>(article);		
		Link link = linkTo(methodOn(ArticleRestController.class).getById(id)).withRel("self");
		articleResource.add(link);		
		
		return ResponseEntity.ok(articleResource);
	}
	
	@GetMapping(path = "/byaccount/{loginId}")
	public ResponseEntity<List<Resource<ArticleView>>> getByLoginId(@PathVariable String loginId){
		return null;
	}
}
