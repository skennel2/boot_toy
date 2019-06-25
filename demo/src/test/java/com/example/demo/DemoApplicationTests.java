package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	AccountRepository accountRepo;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void AccountRepositoryLoads() {
		assertTrue(accountRepo != null);
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
	
	@Before
	public void clearData() {
		accountRepo.deleteAll();
	}
	
}
