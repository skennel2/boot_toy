package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.Article;
import com.example.demo.article.domain.Comment;
import com.example.demo.article.dto.CommentView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;
import com.example.demo.article.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	AccountRepository accountRepository;
	
	private Account account;
	
	private Article article;
	
	@Test
	public void getByWriterIdTest() {
		List<CommentView> comments = commentService.getByWriterId(account.getId());
		
		assertEquals(2, comments.size());
	}
	
	@Test
	public void getByArticleIdTest() {
		List<CommentView> comments = commentService.getByArticleId(article.getId());
		
		assertEquals(2, comments.size());
	}
	
	@Before
	public void before() {
		commentRepository.deleteAll();
		articleRepository.deleteAll();
		accountRepository.deleteAll();

		Account account = new Account("skennel", "1234");
		accountRepository.save(account);

		Article article = new Article("Hello", "Hello World", account); 
		articleRepository.save(article);
		
		Comment comment = new Comment(account, article, "This is Comment");
		Comment comment2 = new Comment(account, article, "This is Comment2");
		commentRepository.save(comment);
		commentRepository.save(comment2);
		
		this.account = account;
		this.article = article;
	}
}
