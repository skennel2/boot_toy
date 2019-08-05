package com.example.demo.article.message;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.article.dto.AddCommentRequest;

@Component
public class EventPublisher {
	private RabbitTemplate rabbitTemplate;

	private AmqpAdmin amqpAdmin;

	@Autowired
	private EventPublisher(RabbitTemplate rabbitTemplate, AmqpAdmin amqpAdmin) {
		this.rabbitTemplate = rabbitTemplate;
		this.amqpAdmin = amqpAdmin;
	}

	public void publishAddCommentEvent(AddCommentRequest request) {

		Queue queue = new Queue("admin.test");
		
		Binding binding = new Binding("admin.test", DestinationType.QUEUE , "amq.direct", "admin.test", null);
				
		amqpAdmin.declareQueue(queue);
		amqpAdmin.declareBinding(binding);

		rabbitTemplate.convertAndSend("comment.add", request);
	}	
}
