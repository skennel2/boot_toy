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

import com.example.demo.article.dto.CommentView;
import com.example.demo.article.service.CommentService;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	@GetMapping(path = "/byarticle/{id}")
	public ResponseEntity<Resource<List<CommentView>>> getByArticleId(@PathVariable Long articleId) {
		List<CommentView> comments = commentService.getByArticleId(articleId);

		Resource<List<CommentView>> articleResource = new Resource<>(comments);
		Link link = linkTo(methodOn(CommentRestController.class).getByArticleId(articleId)).withRel("self");
		articleResource.add(link);

		return ResponseEntity.ok(articleResource);
	}
}
