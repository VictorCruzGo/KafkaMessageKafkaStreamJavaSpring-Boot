package com.vic.kafka.app.broker.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class OrderMessage {
	//Fields Order
	private String creditCardNumber;
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime orderDateTime;
	private String orderLocation;
	private String orderNumber;
	//Fieds OrderItem
	private String itemName;
	private int price;
	private int quantity;
	
	
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditNumber) {
		this.creditCardNumber = creditNumber;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getOrderLocation() {
		return orderLocation;
	}
	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrderMessage [creditNumber=" + creditCardNumber + ", itemName=" + itemName + ", orderDateTime="
				+ orderDateTime + ", orderLocation=" + orderLocation + ", orderNumber=" + orderNumber + ", price="
				+ price + ", quantity=" + quantity + "]";
	}
}
