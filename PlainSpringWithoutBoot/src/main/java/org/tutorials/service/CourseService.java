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
		courses.add(springBoot);
	}


	@Override
	public List<Course> list() {
		// TODO Auto-generated method stub
		return courses;
	}


	@Override
	public Course create(Course t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Course> get(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(Course t, int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	


}
