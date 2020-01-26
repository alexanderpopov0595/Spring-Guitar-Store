package com.springguitarshop.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User implements Serializable {
	private long id;
	//validation parameters
	
	@Size(min=3, max=10, message="Login must be between 3 and 10 characters long")
	@Pattern(regexp="^[a-zA-Z0-9]+$", message="Login must be alphanumeric with no spaces")
	private String login;

	@Size(min=5, max=10, message="Password must be between 5 and 10 characters long")
	private String password;

	@Size(min=3, max=50, message="Username must be between 3 and 50 characters long")
	private String name;

	@Size(min=12, max=12, message="Phone number must be 12 characters long")
	@Pattern(regexp="^[0-9]")
	private String phone;
	
	public User() {
		
	}
	public User(String login, String password, String name, String phone) {
		this.login=login;
		this.password=password;
		this.name=name;
		this.phone=phone;
	}
	public User(long id, String login, String password, String name, String phone) {
		this.id=id;
		this.login=login;
		this.password=password;
		this.name=name;
		this.phone=phone;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	public long getId() {
		return id;
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	public String getLogin() {
		return login;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public String getPhone() {
		return phone;
	}
	

}
