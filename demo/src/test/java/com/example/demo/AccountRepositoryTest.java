package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.article.domain.Account;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

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
	public void AccountRepository_저장테스트() {
		Account account = new Account("skennel", "1234");		
		
		accountRepo.save(account);		
		
		assertTrue(account.getId() != null);
		
		accountRepo.flush();
		
		Optional<Account> accountGet = accountRepo.findById(account.getId());
		
		assertEquals("skennel", accountGet.get().getLoginId());
	}
	
	@Test
	public void persist_auditing_test() {
		Account account = new Account("skennel", "1234");
		
		accountRepo.save(account);
		
		LocalDateTime createdDateTime = account.getCreatedDateTime();
		
		assertEquals(true, Objects.nonNull(createdDateTime));		
		assertEquals(LocalDateTime.now().getYear(), createdDateTime.getYear());
	}
	
	@Before
	public void clearData() {
		commentRepo.deleteAll();
		articleRepo.deleteAll();
		accountRepo.deleteAll();
	}
	
}
