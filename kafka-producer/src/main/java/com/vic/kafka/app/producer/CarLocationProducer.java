package com.vic.kafka.app.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.kafka.app.entity.CarLocation;

@Service
public class CarLocationProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper(); 
	
	public void send(CarLocation carLocation) throws JsonProcessingException {
		var json=objectMapper.writeValueAsString(carLocation); 		
		kafkaTemplate.send("t_location",carLocation.getCarId(),json);
	}
}
