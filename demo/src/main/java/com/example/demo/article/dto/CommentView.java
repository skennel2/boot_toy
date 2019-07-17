package com.example.demo.article.dto;

import java.time.LocalDateTime;

import com.example.demo.article.domain.Comment;

public class CommentView {
	private Long commentId;

	private Long writerId;

	private Long articleId;

	private String contents;

	private LocalDateTime createdDateTime;

	public static CommentView of(Comment entity) {
		CommentView view = new CommentView();
		view.setCommentId(entity.getId());
		view.setArticleId(entity.getArticle().getId());
		view.setWriterId(entity.getWriter().getId());
		view.setContents(entity.getContents());
		view.setCreatedDateTime(entity.getCreatedDateTime());

		return view;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

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

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Override
	public String toString() {
		return "CommentView [commentId=" + commentId + ", writerId=" + writerId + ", articleId=" + articleId
				+ ", contents=" + contents + ", createdDateTime=" + createdDateTime + "]";
	}

}
