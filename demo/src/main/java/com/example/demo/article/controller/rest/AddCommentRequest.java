package com.example.demo.article.controller.rest;

public class AddCommentRequest {

	private Long writerId;
	private Long articleId;
	private String contents;

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "AddCommentRequest [writerId=" + writerId + ", articleId=" + articleId + ", contents=" + contents + "]";
	}

}
