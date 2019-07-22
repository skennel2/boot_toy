package com.example.demo.article.dto;

import java.time.LocalDateTime;

public class ArticleView {
	private Long articleId;
	private String subject;
	private String contents;
	private Long writerId;
	private String writerLoginId;
	private LocalDateTime createdDate;
	private int countOfComments;
	
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
	
	public int getCountOfComments() {
		return countOfComments;
	}

	public void setCountOfComments(int countOfComments) {
		this.countOfComments = countOfComments;
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
