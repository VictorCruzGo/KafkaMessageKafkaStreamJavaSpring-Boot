package com.vic.kafka.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	
	@Column(nullable = false, length = 20)	
	private String orderNumber;
	
	@Column(nullable = false, length = 200)	
	private String orderLocation;
	
	@Column(nullable = false)
	private LocalDateTime orderDateTime;
	
	@Column(nullable = false, length = 20)
	private String creditCardNumber;
	
	//mappedBy, hace referencia a que la relacion ya fue construida por la otra clase OrderItem atraves de su variable order
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;

	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber + ", orderLocation=" + orderLocation
				+ ", orderDateTime=" + orderDateTime + ", creditCardNumber=" + creditCardNumber + ", items=" + items
				+ "]";
	}	
}
