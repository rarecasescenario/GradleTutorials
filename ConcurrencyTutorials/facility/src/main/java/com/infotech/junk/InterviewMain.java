package com.infotech.junk;

public class InterviewMain {

	public static void main(String[] args) {
		new InterviewMain().methodA();
	}
	
	public void methodA() {
		Car car = new Car("Roma","Ferrari");
		callMethodB(car);
		System.out.println("Inside MethodA >>> "+ car.toString());
	}
	
	public void callMethodB(Car car) {
		car.setModel("F8");
		car = new Car("Portofino", "Ferrari");
		System.out.println("Inside MethodB >>>> " + car.toString());
	}
}
