package org.tutorials;

import org.tutorials.service.CourseService;

public class PlainSpringMain {

	public static void main(String[] args) {
		System.out.println("Hello Spring!");
		CourseService service = new CourseService();
		System.out.println(service.list());
	}
}
