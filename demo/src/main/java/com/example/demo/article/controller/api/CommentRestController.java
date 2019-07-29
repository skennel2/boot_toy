package com.example.demo.article.controller.api;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.article.dto.AddCommentRequest;
import com.example.demo.article.dto.CommentView;
import com.example.demo.article.service.CommentService;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentRestController {	
	
	private CommentService commentService;
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public CommentRestController(CommentService commentService, RabbitTemplate rabbitTemplate) {
		this.commentService = commentService;
		this.rabbitTemplate = rabbitTemplate;
	}

	@GetMapping(path = "/byarticle/{articleId}")
	public ResponseEntity<List<CommentView>> getByArticleId(@PathVariable Long articleId) {
		List<CommentView> comments = commentService.getByArticleId(articleId);

		return ResponseEntity.ok(comments);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		commentService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> add(@RequestBody AddCommentRequest request){
		commentService.addComment(request);
		rabbitTemplate.convertAndSend(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
