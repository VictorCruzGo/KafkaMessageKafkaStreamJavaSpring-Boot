package com.vic.kafka.app.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.kafka.app.entity.Employee;

//@Service
public class EmployeeJsonConsumer {

	private ObjectMapper objectMapper=new ObjectMapper();
	
	private static final Logger log=LoggerFactory.getLogger(EmployeeJsonConsumer.class);
	
	@KafkaListener(topics="t_employee_2")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var emp=objectMapper.readValue(message, Employee.class);
		log.info("Employe is {}", emp);
	}
	
}
