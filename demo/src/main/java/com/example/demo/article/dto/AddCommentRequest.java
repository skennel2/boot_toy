package com.example.demo.article.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCommentRequest {
	
	@JsonProperty(value = "writer_id")
	private Long writerId;
	
	@JsonProperty(value = "article_id")
	private Long articleId;
	
	@JsonProperty(value = "contents")
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
