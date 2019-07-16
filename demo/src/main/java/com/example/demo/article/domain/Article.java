package com.example.demo.article.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article extends EntityBase{

	@Column(name = "subject", length = 30, nullable = false)
	private String subject;

	@Column(name = "contents", length = 300, nullable = true)
	private String contents;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "writer")
	private Account writer;
	
	public Article(String subject, String contents, Account writer) {
		this.subject = subject;
		this.contents = contents;
		this.writer = writer;
	}

	protected Article() {
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
	
	public Account getWriter() {
		return this.writer;
	}

	@Override
	public String toString() {
		return "Article [subject=" + subject + ", contents=" + contents + ", writer=" + writer + "]";
	}
	
}
