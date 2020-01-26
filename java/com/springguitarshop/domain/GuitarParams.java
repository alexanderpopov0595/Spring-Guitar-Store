package com.springguitarshop.domain;

import java.io.Serializable;

public class GuitarParams  implements Serializable {
	private int total;
	private String name;
	private String type;
	private int minPrice;
	private int maxPrice;
	private String order;
	
	public GuitarParams() {};
	
	public int getTotal() {
		return total;		
	}
	public void setTotal(int total) {
		this.total=total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.equals("")) {
			//set it to any value
			name="%";		
		}
		//if it is set
		//add % to sql (in case when it's not full name)
		else {
			name="%"+name+"%";
		}
		this.name=name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		//if type is not defined
		if(type.equals("")) {
			type="%";
		}
		this.type=type;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		if(minPrice==null) {
			minPrice=0;
		}
		this.minPrice=minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		if(maxPrice==null) {
			maxPrice=Integer.MAX_VALUE;
		}
		this.maxPrice=maxPrice;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order=order;
	}
}
