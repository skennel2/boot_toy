package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	ArticleRepository articleRepo;
	
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
	public void AccountRepository_저장테스트() {
		Account account = new Account("skennel", "1234");		
		
		accountRepo.save(account);		
		
		assertTrue(account.getId() != null);
		
		accountRepo.flush();
		
		Optional<Account> accountGet = accountRepo.findById(account.getId());
		
		assertEquals("skennel", accountGet.get().getLoginId());
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
	public void persist_auditing_test() {
		Account account = new Account("skennel", "1234");
		
		accountRepo.save(account);
		
		LocalDateTime createdDateTime = account.getCreatedDateTime();
		
		assertEquals(true, Objects.nonNull(createdDateTime));		
		assertEquals(LocalDateTime.now().getYear(), createdDateTime.getYear());
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
		articleRepo.deleteAll();
		accountRepo.deleteAll();
	}
	
}
