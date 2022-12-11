package com.pswb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pswb.config.AppConfig;
import com.pswb.service.CourseService;

public class PswbMain {

	private static final Logger log = LogManager.getLogger(PswbMain.class);
	
	public static void main(String[] args) {
//		System.out.println("Hello World!!");
		log.info("pswb application started...");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		CourseService cs = ctx.getBean("courseService", CourseService.class);
		
		cs.list().forEach(t -> {
			log.info(t.getTitle());
		});
		log.info(cs.list());
		
	}

}
