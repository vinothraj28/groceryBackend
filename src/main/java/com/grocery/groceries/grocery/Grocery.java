package com.grocery.groceries.grocery;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Grocery {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private double price;
	private Date date;
	
	public Grocery(){
		
	}

	public Grocery(long id, String name, double price, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
	}

	public Grocery(String name, double price, Date date) {
		super();
		this.name = name;
		this.price = price;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "grocery [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + "]";
	}
	
	
}
