package com.example.demo.article.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.Article;
import com.example.demo.article.domain.exception.AccountNotFoundException;
import com.example.demo.article.domain.exception.ArticleNotFoundException;
import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepo;
	
	private AccountRepository accountRepo;	
	
	@Autowired
	public ArticleService(ArticleRepository articleRepo, AccountRepository accountRepo) {
		this.articleRepo = articleRepo;
		this.accountRepo = accountRepo;
	}

	public ArticleView getById(Long id) {
		return articleRepo.findById(id)
				.map(ArticleView::of)
				.orElseThrow(()-> new ArticleNotFoundException());
	}
	
	public List<ArticleView> getByWriterId(Long writerId){
		return articleRepo.findByWriterId(writerId)
				.stream()
				.map(ArticleView::of)
				.collect(Collectors.toList());
	}
	
	public List<ArticleView> getByWriterLoginId(String writerLoginId){
		return articleRepo.findByWriterLoginId(writerLoginId)
				.stream()
				.map(ArticleView::of)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void addPost(Long writerId, String subject, String contents) {
		Account writer = accountRepo.findById(writerId)
				.orElseThrow(() -> new AccountNotFoundException());
		
		Article newArticle = new Article(subject, contents, writer);		
		articleRepo.save(newArticle);
	}
}
