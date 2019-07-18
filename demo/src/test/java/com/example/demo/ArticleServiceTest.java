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
import com.example.demo.article.domain.exception.ArticleNotFoundException;
import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;
import com.example.demo.article.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

	@Autowired
	ArticleService articleService;

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CommentRepository commentRepository;
	
	private Account account;

	@Test
	public void addPost_getByWriterIdTest() {
		articleService.addPost(account.getId(), "Hello", "Hello World");

		ArticleView article = articleService.getByWriterId(account.getId()).get(0);

		assertEquals("Hello", article.getSubject());
	}

	@Test
	public void addPost_getByWriterLoginIdTest() {
		articleService.addPost(account.getId(), "Hello", "Hello World");

		List<ArticleView> articles = articleService.getByWriterLoginId(account.getLoginId());
		ArticleView articleView = articles.get(0);

		assertEquals("Hello", articleView.getSubject());
		assertEquals("Hello World", articleView.getContents());
		assertEquals(account.getLoginId(), articleView.getWriterLoginId());
	}

	@Test(expected = ArticleNotFoundException.class)
	public void 비어있는상태에서_getByIdTest() {
		articleService.getById(new Long(100));
	}
	
	@Before
	public void before() {
		commentRepository.deleteAll();
		articleRepository.deleteAll();
		accountRepository.deleteAll();

		Account account = new Account("skennel", "1234");
		accountRepository.save(account);

		this.account = account;
	}
}
