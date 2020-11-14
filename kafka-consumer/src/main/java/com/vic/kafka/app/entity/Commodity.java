package com.vic.kafka.app.entity;

public class Commodity {
	private String name;
	private double price;
	private String meassurement;
	private long timestamp;
	
	public Commodity() {}
		
	public Commodity(String name, double price, String meassurement, long timestamp) {
		super();
		this.name = name;
		setPrice(price);	
		this.meassurement = meassurement;
		this.timestamp = timestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = Math.round(price*100d)/100d;
	}
	public String getMeassurement() {
		return meassurement;
	}
	public void setMeassurement(String meassurement) {
		this.meassurement = meassurement;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "Commodity [name=" + name + ", price=" + price + ", meassurement=" + meassurement + ", timestamp="
				+ timestamp + "]";
	}
	
	
}
