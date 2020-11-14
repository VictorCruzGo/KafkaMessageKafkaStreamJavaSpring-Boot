package com.vic.kafka.app.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.vic.kafka.app.broker.message.DiscountMessage;
import com.vic.kafka.app.broker.message.PromotionMessage;

//@Service
//@KafkaListener(topics = "t.commodity.promotion")
public class PromotionListener {
	
	private final static Logger LOG=LoggerFactory.getLogger(PromotionListener.class);
	
	@KafkaHandler
	public void listenDiscount(DiscountMessage message) {
		LOG.info("Processing discount : {}", message);
	}

	@KafkaHandler
	public void listenPromotion(PromotionMessage message) {
		LOG.info("Processing promotion : {}", message);
	}
}
