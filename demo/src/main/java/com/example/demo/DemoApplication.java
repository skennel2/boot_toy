package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.Article;
import com.example.demo.article.domain.Comment;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;

/**
 * Spring Security를 이용한 Basic Authentication https://fntg.tistory.com/191
 * https://www.popit.kr/spring-security-ajax-%ED%98%B8%EC%B6%9C-%EC%8B%9C-csrf-%EA%B4%80%EB%A0%A8-403-forbidden-%EC%97%90%EB%9F%AC/
 * https://www.mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/
 * http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
 * https://m.blog.naver.com/PostView.nhn?blogId=wizardkyn&logNo=220647117154&proxyReferer=https%3A%2F%2Fwww.google.com%2F
 * @author skennel
 *
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		commentRepository.deleteAll();
		articleRepository.deleteAll();
		accountRepository.deleteAll();
		
		// TODO {nopo}을 붙인이유 https://www.mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/
		Account account = new Account("skennel", "{noop}1234");
		accountRepository.save(account);
		
		Article article = new Article("Hello", "Hello World", account); 
		articleRepository.save(article);
		
		Article article2 = new Article("This is Mock Article", "Hello World", account); 
		articleRepository.save(article2);
		
		Article articleMock;
		for (int i = 0; i < 500; i++) {
			articleMock = new Article("Test".concat(String.valueOf(i)), String.valueOf(i), account); 
			articleRepository.save(articleMock);
		}
		
		Comment comment = new Comment(account, article, "This is Comment");
		Comment comment2 = new Comment(account, article, "This is Comment2");
		commentRepository.save(comment);
		commentRepository.save(comment2);		
	}
}
