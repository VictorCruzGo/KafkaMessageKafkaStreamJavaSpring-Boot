package com.vic.kafka.app.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vic.kafka.app.scheduler.CommodityScheduler;

//@Service
public class RebalancingProducer {

	private static final Logger log= LoggerFactory.getLogger(RebalancingProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private int i=0;
	
	@Scheduled(fixedRate = 1000) //velocidad fija
	public void sendMessage() {
		i++;
		log.info("t_rebalancing - Counter is "+i);
		kafkaTemplate.send("t_rebalancing_2","Counter is "+i);
	}
}
