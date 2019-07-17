package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.article.domain.Account;
import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
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
	
	private Long writerId;
	
	public void getByWriterIdTest() {				
		articleService.addPost(writerId, "Hello", "Hello World");
		
		ArticleView article = articleService.getByWriterId(writerId).get(0);
		
		assertEquals("Hello", article.getSubject());
	}
	
	@Before
	public void before() {
		articleRepository.deleteAll();
		accountRepository.deleteAll();
		
		Account account = new Account("skennel", "1234");		
		accountRepository.save(account);
		
		writerId = account.getId();
	}
}
