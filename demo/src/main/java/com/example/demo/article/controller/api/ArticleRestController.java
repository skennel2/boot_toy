package com.example.demo.article.controller.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

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
		return ResponseEntity.ok(toCommentLinkAddedResource(article));
	}
	
	@GetMapping(path = "/byaccount/{loginId}")
	public ResponseEntity<List<Resource<ArticleView>>> getByLoginId(@PathVariable String loginId){
		List<Resource<ArticleView>> list = articleService.getByWriterLoginId(loginId).stream()
			.map(this::toCommentLinkAddedResource)
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(list);
	}
	
	private Resource<ArticleView> toCommentLinkAddedResource(ArticleView articleView) {
		Resource<ArticleView> articleResource = new Resource<>(articleView);		
		Link link = linkTo(methodOn(CommentRestController.class).getByArticleId(articleView.getId()))
				.withRel("comments")
				.withTitle("comment_list");
		articleResource.add(link);		
		
		return articleResource;
	}
}
