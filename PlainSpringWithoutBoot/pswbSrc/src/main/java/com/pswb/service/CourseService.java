package com.pswb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pswb.model.Course;
import com.pswb.repository.CourseRepository;

@Service
public class CourseService implements CrudService<Course> {
	
	private CourseRepository repository;
	
	public CourseService(CourseRepository courseRepository) {
		repository = courseRepository;
	}

	@Override
	public List<Course> list() {
		return repository.findAll();
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
		return "CourseService [repository=" + repository + ", list()=" + list() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


}
