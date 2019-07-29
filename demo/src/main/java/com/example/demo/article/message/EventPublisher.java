package com.example.demo.article.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.article.dto.AddCommentRequest;

@Component
public class EventPublisher {
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public EventPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void publishAddCommentEvent(AddCommentRequest request) {
		rabbitTemplate.convertAndSend("comment.add", request);
	}
}
