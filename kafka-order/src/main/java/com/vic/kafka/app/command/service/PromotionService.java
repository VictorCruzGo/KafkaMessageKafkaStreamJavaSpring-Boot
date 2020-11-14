package com.vic.kafka.app.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vic.kafka.app.api.request.PromotionRequest;
import com.vic.kafka.app.command.action.PromotionAction;

@Service
public class PromotionService {

	@Autowired
	private PromotionAction action;
	
	public void createPromotion(PromotionRequest request) {
		action.publishToKafka(request);
	}
}
