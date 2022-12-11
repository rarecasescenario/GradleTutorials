package com.pswb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pswb.model.Course;

@Repository
public class CourseRepository implements CrudRepository<Course> {
	List<Course> courses = new ArrayList<>();

	@Override
	public List<Course> findAll() {
		courses = new ArrayList<>();
		Course springBoot = new Course(1, "Getting Started with Spring", "Learn Spring without Boot",
				"https://spring.io");
		Course plainSpring = new Course(2, "Getting Started with PlainSpring", "Plain Spring with Application Context",
				"https://spring.io/docs");
		courses.add(springBoot);
		courses.add(plainSpring);
		return courses;
	}

}
