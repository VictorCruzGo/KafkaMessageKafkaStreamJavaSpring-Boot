package com.vic.kafka.app.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vic.kafka.app.api.request.DiscountRequest;
import com.vic.kafka.app.broker.message.DiscountMessage;
import com.vic.kafka.app.broker.producer.DiscountProducer;

@Component
public class DiscountAction {

	@Autowired
	private DiscountProducer producer;

	public void publishToKafka(DiscountRequest request) {
		var message = new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
		producer.publish(message);
	}

}
