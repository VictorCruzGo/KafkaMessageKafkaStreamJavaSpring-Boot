package com.vic.kafka.app.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vic.kafka.app.entity.Commodity;
import com.vic.kafka.app.producer.CommodityProducer;

//@Service
public class CommodityScheduler {				
    private static final Logger log = LoggerFactory.getLogger(CommodityScheduler.class);
    
	private RestTemplate restTemplate=new RestTemplate();
	
	@Autowired
	private CommodityProducer commodityProducer;
	
	@Scheduled(fixedRate = 5000)
	public void fetchCommodities() {
		var commodities=restTemplate.exchange("http://localhost:8080/api/commodity/v1/all", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<Commodity>>() {
				}).getBody();
		
		commodities.forEach(c->{
			try {
				log.info("send message {}", c);
				commodityProducer.sendMessage(c);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
