package com.vic.kafka.app.broker.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.vic.kafka.app.broker.message.OrderMessage;

//@Service
public class OrderListener {

	private static final Logger LOG=LoggerFactory.getLogger(OrderListener.class);
	
	@KafkaListener(topics="t.commodity.order")
	public void listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
		//sometimes trow exception because existing message witout head
		var headers=consumerRecord.headers();
		var orderMessage=consumerRecord.value();
		
		LOG.info("Processing order location:{}, order:{}, item:{}, credit card number:{}"
				, orderMessage.getOrderLocation()
				, orderMessage.getOrderNumber()
				, orderMessage.getItemName()
				, orderMessage.getCreditCardNumber());
		
		LOG.info("Header are:");
		headers.forEach(h-> LOG.info(" key:{} value:{}", h.key(), new String(h.value())));
		
		var bonusPercentage	= Double.parseDouble(new String(headers.lastHeader("surpriseBonus").value()));
		var bonusAmount=(bonusPercentage/100)*orderMessage.getPrice()*orderMessage.getQuantity();
		
		LOG.info("surprise bonus is {}", bonusAmount);
	}
}
