package com.example.demo.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.article.domain.Article;
import com.example.demo.article.dto.ArticleView;
import com.example.demo.article.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepo;
	
	public ArticleView getById(Long id) {
		return articleRepo.findById(id)
				.map(this::toViewModel)
				.orElseThrow(()-> new RuntimeException());
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
