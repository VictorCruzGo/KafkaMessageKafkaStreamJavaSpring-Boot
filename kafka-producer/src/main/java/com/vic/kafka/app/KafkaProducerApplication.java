package com.vic.kafka.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vic.kafka.app.entity.Employee;
import com.vic.kafka.app.producer.EmployeeJsonProducer;
import com.vic.kafka.app.producer.HelloKafkaProducer;
import com.vic.kafka.app.producer.KafkaKeyProducer;
import com.vic.kafka.app.scheduler.FixedRateScheduler;

import jdk.jfr.Enabled;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner{
	
	@Autowired
	public HelloKafkaProducer helloKafkaProducer;
	
	//@Autowired
	//public FixedRateScheduler fixedRateProducer;
	
	@Autowired
	public KafkaKeyProducer kafkaKeyProducer;
	
	@Autowired
	public EmployeeJsonProducer employeeJsonProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//helloProducer();
		//fixedRateProducer();
		//keyProducer();
		//employeeJsonProducer();
	}
	
	public void keyProducer() throws InterruptedException {
		for (int i = 0; i < 30; i++) {
			var key="key"+(i%4);
			var data="data "+i+" with key "+key;
			kafkaKeyProducer.send(key, data);
			Thread.sleep(500);
		}
	}
	
	//@EnableScheduling. Para este ejemplo solo habiltar la anotacion mencionada. No es necesario este metodo
	public void helloProducer() {
		System.out.println("Se enviara tu nombre...");
		helloKafkaProducer.sendHello("Victor "+Math.random());
		System.out.println("Se envio tu nombre...");		
	}
	
//	public void fixedRateProducer() {
//		fixedRateProducer();
//	}
	
	public void employeeJsonProducer() throws JsonProcessingException {
		for (int i = 0; i < 5; i++) {
			var employee=new Employee("emp-"+i, "Employe "+i, LocalDate.now());
			employeeJsonProducer.sendMessage(employee);
		}
	}
}
