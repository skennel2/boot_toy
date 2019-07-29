package com.example.demo.article.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.article.dto.AddCommentRequest;

@Component
public class CommentMessageReceiver {
	
	@RabbitListener(queues = "comment.add")
	public void processMessage(AddCommentRequest request) {
		System.out.println(request.toString());		
	}
	
}
