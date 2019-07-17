package com.example.demo.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findBySubject(String subject);
	List<Article> findByWriterId(Long id);
	List<Article> findByWriterLoginId(String loginId);
}
