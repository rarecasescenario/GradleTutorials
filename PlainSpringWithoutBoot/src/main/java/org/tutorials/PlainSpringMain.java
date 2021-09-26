package org.tutorials;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tutorials.config.AppConfig;
import org.tutorials.service.CourseService;

public class PlainSpringMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		CourseService cs = ctx.getBean("courseService",CourseService.class);
		cs.list().forEach(t -> {
			System.out.println(t.getDescription());
		});
	}
}
