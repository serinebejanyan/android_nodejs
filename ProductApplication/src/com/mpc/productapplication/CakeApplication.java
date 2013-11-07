package com.mpc.productapplication;

public class CakeApplication {
	
	protected int id;
	protected String brand;
	protected float price;
	protected float weight;
	protected String type;
	protected String name;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static CakeApplication generateCake(){
		CakeApplication cake = new CakeApplication();
		cake.setName("nutella");
		cake.setType("creamed");
		cake.setBrand("Dan Desert");
		
		return cake;
	}
	
}