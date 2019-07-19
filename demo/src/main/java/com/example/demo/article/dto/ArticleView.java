package com.example.demo.article.dto;

import java.time.LocalDateTime;

import com.example.demo.article.domain.Article;

public class ArticleView {
	private Long articleId;
	private String subject;
	private String contents;
	private Long writerId;
	private String writerLoginId;
	private LocalDateTime createdDate;

	public static ArticleView of(Article article) {
		ArticleView av = new ArticleView();
		av.setArticleId(article.getId());
		av.setSubject(article.getSubject());
		av.setContents(article.getContents());
		av.setWriterId(article.getWriter().getId());
		av.setWriterLoginId(article.getWriter().getLoginId());
		av.setCreatedDate(article.getCreatedDateTime());
		return av;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long id) {
		this.articleId = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public String getWriterLoginId() {
		return writerLoginId;
	}

	public void setWriterLoginId(String writerLoginId) {
		this.writerLoginId = writerLoginId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "ArticleView [id=" + articleId + ", subject=" + subject + ", contents=" + contents + ", writerId=" + writerId
				+ ", writerLoginId=" + writerLoginId + ", createdDate=" + createdDate + "]";
	}

}
