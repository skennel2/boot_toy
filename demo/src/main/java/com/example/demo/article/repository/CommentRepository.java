package com.example.demo.article.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.article.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByWriterId(Long id);
	List<Comment> findByArticleId(Long id, Sort sort);
}
