package com.vic.kafka.app.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vic.kafka.app.api.request.OrderRequest;
import com.vic.kafka.app.command.action.OrderAction;
import com.vic.kafka.app.entity.Order;


@Service
public class OrderService {
	@Autowired
	private OrderAction action;
	
	public String saveOrder(OrderRequest request) {
		//1. convert OrderRequest to Order
		Order order=action.convertToOrder(request);
		//2. save order to database
		action.saveToDataBase(order);
		//3. flatten the items & orders as kafka message and publish
		order.getItems().forEach(action::publishToKafka);
		//4. return order number (auto generate)
		return order.getOrderNumber();
	}
}
