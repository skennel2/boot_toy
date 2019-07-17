package com.example.demo.article.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.AccountNotFoundException;
import com.example.demo.article.domain.Article;
import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepo;
	
	private AccountRepository accountRepo;	
	
	@Autowired
	private ArticleService(ArticleRepository articleRepo, AccountRepository accountRepo) {
		this.articleRepo = articleRepo;
		this.accountRepo = accountRepo;
	}

	public ArticleView getById(Long id) {
		return articleRepo.findById(id)
				.map(this::toViewModel)
				.orElseThrow(()-> new RuntimeException());
	}
	
	public List<ArticleView> getByWriterId(Long writerId){
		return articleRepo.findByWriterId(writerId)
				.stream()
				.map(this::toViewModel)
				.collect(Collectors.toList());
	}
	
	public List<ArticleView> getByWriterLoginId(String writerLoginId){
		return articleRepo.findByWriterLoginId(writerLoginId)
				.stream()
				.map(this::toViewModel)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void addPost(Long writerId, String subject, String contents) {
		Account writer = accountRepo.findById(writerId)
				.orElseThrow(() -> new AccountNotFoundException());
		
		Article newArticle = new Article(subject, contents, writer);		
		articleRepo.save(newArticle);
	}
	
	private ArticleView toViewModel(Article article) {
		ArticleView av = new ArticleView();
		av.setId(article.getId());
		av.setSubject(article.getSubject());
		av.setContents(article.getContents());
		av.setWriterId(article.getWriter().getId());
		av.setWriterLoginId(article.getWriter().getLoginId());
		av.setCreatedDate(article.getCreatedDateTime());
		return av;
	}
}
