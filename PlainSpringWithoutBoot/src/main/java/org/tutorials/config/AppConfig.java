package org.tutorials.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tutorials.service.CourseService;

@Configuration
public class AppConfig {

	@Bean("courseService")
	public CourseService getCourseService() {
		return new CourseService();
	}
	
	
}
