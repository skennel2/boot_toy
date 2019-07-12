package com.example.demo.article.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article extends EntityBase{

	@Column(name = "subject", length = 30, nullable = false)
	private String subject;

	@Column(name = "contents", length = 300, nullable = true)
	private String contents;
	
	public Article(String subject, String contents) {
		this.subject = subject;
		this.contents = contents;
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
}
