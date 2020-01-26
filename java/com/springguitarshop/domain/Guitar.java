package com.springguitarshop.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Guitar implements Serializable{
	private long id;

	private User user;
	@Size(min=3, max=50, message="Username must be between 3 and 50 characters long")
	private String name;
	private String type;
	private int price;
	@Size(min=3, max=255, message="Message must be between 3 and 255 characters long")
	private String description;
	private Date date;
	
	public Guitar() {
		
	}
	public Guitar(User user, String name, String type, int price, String description, Date date) {
		this.user=user;
		this.name=name;
		this.type=type;
		this.price=price;
		this.description=description;
		this.date=date;
	}
	//for pages with full guitar information
	public Guitar(long id, User user, String name, String type, int price, String description, Date date) {
		this.id=id;
		this.user=user;
		this.name=name;
		this.type=type;
		this.price=price;
		this.description=description;
		this.date=date;
	}
	//for pages with guitar's list
	public Guitar( String name, String type, int price, String description, Date date) {		
		this.name=name;
		this.type=type;
		this.price=price;
		this.description=description;
		this.date=date;
	}
	public void setId(long id) {
		this.id=id;
	}
	public long getId() {
		return id;
	}
	public void setUser(User user) {
		this.user=user;
	}
	public User getUser() {
		return user;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setType(String type) {
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	public int getPrice() {
		return price;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
	public void setDate(Date date) {
		this.date=date;
	}
	public Date getDate() {
		return date;
	}
}
