package com.vic.kafka.app.command.action;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vic.kafka.app.api.request.OrderItemRequest;
import com.vic.kafka.app.api.request.OrderRequest;
import com.vic.kafka.app.broker.message.OrderMessage;
import com.vic.kafka.app.broker.producer.OrderProducer;
import com.vic.kafka.app.entity.Order;
import com.vic.kafka.app.entity.OrderItem;
import com.vic.kafka.app.repository.IOrderItemRepository;
import com.vic.kafka.app.repository.IOrderRepository;

@Component
public class OrderAction {
	@Autowired
	private OrderProducer producer;
	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private IOrderItemRepository orderItemRepository;	
	
	public Order convertToOrder(OrderRequest request) {
		var result=new Order();
		result.setCreditCardNumber(request.getCreditCardNumber());
		result.setOrderLocation(request.getOrderLocation());
		result.setOrderDateTime(LocalDateTime.now());
		result.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());
		
		List<OrderItem> items=request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
		items.forEach(item->item.setOrder(result));
		
		result.setItems(items);
		
		return result;
	}

	public OrderItem convertToOrderItem(OrderItemRequest itemRequest) {
		var result=new OrderItem();
		
		result.setItemName(itemRequest.getItemName());
		result.setPrice(itemRequest.getPrice());
		result.setQuantity(itemRequest.getQuantity());
		
		return result;		
	}
	
	public void saveToDataBase(Order order) {
		orderRepository.save(order);
		//order.getItems().forEach(orderItemRepository::save);
		order.getItems().forEach( items-> orderItemRepository.save(items));
	}

	public void publishToKafka(OrderItem item) {
		//flatten de items and order
		var orderMessage=new OrderMessage();
		//items (items detail)
		orderMessage.setItemName(item.getItemName());
		orderMessage.setPrice(item.getPrice());
		orderMessage.setQuantity(item.getQuantity());
		//order
		orderMessage.setOrderDateTime(item.getOrder().getOrderDateTime());
		orderMessage.setOrderLocation(item.getOrder().getOrderLocation());
		orderMessage.setOrderNumber(item.getOrder().getOrderNumber());
		orderMessage.setCreditCardNumber(item.getOrder().getCreditCardNumber());		
		
		producer.publish(orderMessage);
	}
}
