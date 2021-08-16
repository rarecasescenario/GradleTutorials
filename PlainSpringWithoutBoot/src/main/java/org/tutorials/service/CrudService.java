package org.tutorials.service;

import java.util.List;
import java.util.Optional;

import org.tutorials.model.Course;

public interface CrudService<T> {
	List<Course> list();

	T create(T t);

	Optional<T> get(int id);

	void update(T t, int id);

	void delete(int id);
}
