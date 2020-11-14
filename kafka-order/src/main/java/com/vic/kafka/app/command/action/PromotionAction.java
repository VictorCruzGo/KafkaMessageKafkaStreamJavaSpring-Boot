package com.vic.kafka.app.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vic.kafka.app.api.request.PromotionRequest;
import com.vic.kafka.app.broker.message.PromotionMessage;
import com.vic.kafka.app.broker.producer.PromotionProducer;

@Component
public class PromotionAction {

	@Autowired
	private PromotionProducer producer;
	
	public void publishToKafka(PromotionRequest request) {
		var message=new PromotionMessage(request.getPromotionCode());
		
		producer.publish(message);
	}

}
