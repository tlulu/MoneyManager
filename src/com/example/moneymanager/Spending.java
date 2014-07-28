package com.example.moneymanager;

import java.sql.Timestamp;

public class Spending {
	private int id;
	private double price;
	private String category;
	private String time;
	
	public int getID(){
		return id;
	}
	
	public void setID(int id){
		this.id=id;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setCategory(String category){
		this.category=category;
	}
	
	public void setTime(String  time){
		this.time=time;
	}
	
	public String getTime(){
		return time;
	}
	
	
	@Override
	public String toString(){
		return price+" "+"Added: "+time;
	}
	
}
