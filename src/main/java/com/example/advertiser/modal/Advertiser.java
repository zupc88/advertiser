package com.example.advertiser.modal;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class Advertiser implements Serializable {
	

	
	private static final long serialVersionUID = 8604990093149376515L;
	
	private int id;
	private String name;
	private String contactName;
	private float creditLimit;

	public Advertiser() {};
	public Advertiser(int id) {
		super();
		this.id = id;
	}
	
	public Advertiser(int id, String name, String contactName, float creditLimit) {
		super();
		this.id = id;
		this.name = name;
		this.contactName = contactName;
		this.creditLimit = creditLimit;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(float creditLimit) {
		this.creditLimit = creditLimit;
	}
}
