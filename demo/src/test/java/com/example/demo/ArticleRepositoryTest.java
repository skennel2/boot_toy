package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.Article;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	ArticleRepository articleRepo;
	
	@Autowired
	CommentRepository commentRepo;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void AccountRepositoryLoads() {
		assertTrue(accountRepo != null);
	}
	
	@Test
	public void ArticleRepositoryLoads() {
		assertTrue(articleRepo != null);
	}
	
	@Test
	public void ArticleRepository_저장테스트() {
		Account account = new Account("skennel", "1234");	
		
		accountRepo.save(account);
		
		Article article = new Article("Test", "냉무", account);
		
		articleRepo.save(article);
		articleRepo.flush();
		
		Article articleGet = articleRepo.findById(article.getId()).get();
		
		String writerId = articleGet.getWriter().getLoginId();
		
		assertEquals(writerId, account.getLoginId());
	}
	
	@Test
	public void getByWriterIdTest() {
		Account account = new Account("skennel", "1234");	
		accountRepo.save(account);
		
		Article article = new Article("Test", "냉무", account);
		Article article2 = new Article("Test2", "냉무", account);
		
		articleRepo.save(article);
		articleRepo.save(article2);
		articleRepo.flush();
		
		List<Article> result = articleRepo.findByWriterId(account.getId());
		
		assertEquals(2, result.size());
	}
	
	@Before
	public void clearData() {
		commentRepo.deleteAll();
		articleRepo.deleteAll();
		accountRepo.deleteAll();
	}
	
}
