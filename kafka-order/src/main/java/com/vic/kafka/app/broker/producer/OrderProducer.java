package com.vic.kafka.app.broker.producer;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.vic.kafka.app.broker.message.OrderMessage;

@Service
public class OrderProducer {
	
	private static final Logger LOG=LoggerFactory.getLogger(OrderProducer.class);
	
	@Autowired
	private KafkaTemplate<String, OrderMessage> kafkaTemplate;
	
	//Metodo para agregar un header al mensaje.
	private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message){
		//Las sucursales que empizan con "A" recibiran el 25% del bono, el resto 15%
		int surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A")?25:15;
		
		//Los datos que se envian en la cabecera son del tipy array byte
		List<Header> headers=new ArrayList<>();
		var surpriseBonusHeader=new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());
		headers.add(surpriseBonusHeader);
		
		return new ProducerRecord<String, OrderMessage>("t.commodity.order", null, message.getOrderNumber(), message, headers);			
	}
	
	public void publish(OrderMessage message) {
		var producerRecord=buildProducerRecord(message);
		
		//kafkaTemplate.send("t.commodity.order",message.getOrderNumber(), message)
		
		kafkaTemplate.send(producerRecord)		
		.addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {

			@Override
			public void onSuccess(SendResult<String, OrderMessage> result) {
				LOG.info("Order{}, item{} published successfuly", message.getOrderNumber(), message.getItemName());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.info("Order{}, item{} failed to publish because {}", message.getOrderNumber());
				//do something else, maybe inserting to log database, etc
			}
		});
		
		LOG.info("Just a dummy message for order {}, item {} published successfuly", message.getOrderNumber());
	}
}
