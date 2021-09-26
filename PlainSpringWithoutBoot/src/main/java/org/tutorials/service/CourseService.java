package org.tutorials.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.tutorials.model.Course;

public class CourseService implements CrudService<Course> {

	private List<Course> courses;
	
	
	public CourseService() {
		courses = new ArrayList<>();
		Course springBoot = new Course(1, "Getting Started with Spring", "Learn Spring without Boot", "https://spring.io");
		Course plainSpring = new Course(2, "Getting Started with PlainSpring", "Plain Spring with Application Context", "https://spring.io/docs");
		courses.add(springBoot);
		courses.add(plainSpring);
	}


	@Override
	public List<Course> list() {
		return courses;
	}


	@Override
	public Course create(Course t) {
		return null;
	}


	@Override
	public Optional<Course> get(int id) {
		return Optional.empty();
	}


	@Override
	public void update(Course t, int id) {
	
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString() {
		return "CourseService [courses=" + courses + ", list()=" + list() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
