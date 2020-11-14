package com.vic.kafka.app.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vic.kafka.app.json.CustomLocalDateDeserializer;
import com.vic.kafka.app.json.CustomLocalDateSerializer;

public class Employee {
	@JsonProperty("employee_id")
	private String employeeId;
	private String name;
	@JsonProperty("birth_date")
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private LocalDate birthDate;
	
	public Employee() {}
	
	public Employee(String employeeId, String name, LocalDate birthDate) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.birthDate = birthDate;
	}
	public String getEmployeId() {
		return employeeId;
	}
	public void setEmployeId(String employeId) {
		this.employeeId = employeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}
