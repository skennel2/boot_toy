package com.example.demo.article.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.Article;
import com.example.demo.article.domain.exception.AccountNotFoundException;
import com.example.demo.article.domain.exception.ArticleNotFoundException;
import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;

@Service
public class ArticleService {
	
	private final ArticleRepository articleRepo;
	
	private final AccountRepository accountRepo;	
	
	private final CommentRepository commentRepo;
	
	@Autowired
	public ArticleService(ArticleRepository articleRepo, AccountRepository accountRepo, CommentRepository commentRepo) {
		this.articleRepo = articleRepo;
		this.accountRepo = accountRepo;
		this.commentRepo = commentRepo;
	}

	public ArticleView getById(Long id) {
		return articleRepo.findById(id)
				.map(this::toViewModel)
				.orElseThrow(()-> new ArticleNotFoundException());
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
	
	public List<ArticleView> getAllByPaging(int startIndex, int amountOfItem){
		Pageable pageable = PageRequest.of(startIndex, amountOfItem, new Sort(Direction.DESC, "Id"));
		return articleRepo.findAll(pageable)
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
	
	public ArticleView toViewModel(Article article) {
		ArticleView av = new ArticleView();
		av.setArticleId(article.getId());
		av.setSubject(article.getSubject());
		av.setContents(article.getContents());
		av.setWriterId(article.getWriter().getId());
		av.setWriterLoginId(article.getWriter().getLoginId());
		av.setCreatedDate(article.getCreatedDateTime());
		av.setCountOfComments(commentRepo.countByArticleId(article.getId()));
		return av;
	}
}
