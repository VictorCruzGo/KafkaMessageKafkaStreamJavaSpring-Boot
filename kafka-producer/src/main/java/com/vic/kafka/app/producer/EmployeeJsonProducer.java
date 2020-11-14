package com.vic.kafka.app.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.kafka.app.entity.Employee;

@Service
public class EmployeeJsonProducer {

	@Autowired
	private KafkaTemplate<String, String> KafkaTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper(); 
	
	public void sendMessage(Employee emp) throws JsonProcessingException {
		var json=objectMapper.writeValueAsString(emp);
		KafkaTemplate.send("t_employee_2",json);
	}
}
