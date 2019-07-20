package com.example.demo.article.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comment extends EntityBase {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writer")
	private Account writer;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "article")
	private Article article;

	@Column(name = "contents", length = 1000)
	private String contents;

	public Comment(Account writer, Article targetArticle, String contents) {
		this.writer = writer;
		this.article = targetArticle;
		this.contents = contents;
	}

	protected Comment() {
	}

	public Account getWriter() {
		return writer;
	}

	public Article getArticle() {
		return article;
	}

	public String getContents() {
		return contents;
	}

	@Override
	public String toString() {
		return "Comment [writer=" + writer + ", article=" + article + ", contents=" + contents + "]";
	}

}
