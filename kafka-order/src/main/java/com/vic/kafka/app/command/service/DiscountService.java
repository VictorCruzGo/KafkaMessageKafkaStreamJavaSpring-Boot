package com.vic.kafka.app.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vic.kafka.app.api.request.DiscountRequest;
import com.vic.kafka.app.command.action.DiscountAction;

@Service
public class DiscountService {

	@Autowired
	private DiscountAction action;

	public void createDiscount(DiscountRequest request) {
		action.publishToKafka(request);
	}

}
