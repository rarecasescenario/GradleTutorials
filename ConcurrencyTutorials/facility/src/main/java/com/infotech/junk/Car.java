package com.infotech.junk;

public class Car {
	private String model;
	private String maker;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Car(String model, String maker) {
		super();
		this.model = model;
		this.maker = maker;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", maker=" + maker + "]";
	}
}
