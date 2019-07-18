package com.example.demo.article.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.Article;
import com.example.demo.article.domain.Comment;
import com.example.demo.article.domain.exception.AccountNotFoundException;
import com.example.demo.article.domain.exception.ArticleNotFoundException;
import com.example.demo.article.dto.CommentView;
import com.example.demo.article.repository.AccountRepository;
import com.example.demo.article.repository.ArticleRepository;
import com.example.demo.article.repository.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private ArticleRepository articleRepository;
	private AccountRepository accountRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository,
			AccountRepository accountRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
		this.accountRepository = accountRepository;
	}

	@Transactional
	public void addComment(Long writerId, Long articleId, String contents) {
		Account writer = accountRepository
				.findById(writerId)
				.orElseThrow(() -> new AccountNotFoundException());

		Article article = articleRepository
				.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException());

		Comment comment = new Comment(writer, article, contents);
		commentRepository.save(comment);
	}
	
	@Transactional
	public void deleteById(Long id) {
		commentRepository.deleteById(id);
	}

	public List<CommentView> getByWriterId(Long writerId) {
		return commentRepository
				.findByWriterId(writerId)
				.stream()
				.map(CommentView::of)
				.collect(Collectors.toList());
	}
	
	public List<CommentView> getByArticleId(Long articleId) {
		return commentRepository
				.findByArticleId(articleId)
				.stream()
				.map(CommentView::of)
				.collect(Collectors.toList());
	}
}
