package com.example.demo.article.dto;

import java.time.LocalDateTime;

public class ArticleView {
	private Long id;
	private String subject;
	private String contents;
	private Long writerId;
	private String writerLoginId;
	private LocalDateTime createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "ArticleView [id=" + id + ", subject=" + subject + ", contents=" + contents + ", writerId=" + writerId
				+ ", writerLoginId=" + writerLoginId + ", createdDate=" + createdDate + "]";
	}

}
