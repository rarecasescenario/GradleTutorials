package com.pswb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pswb.service.CourseService;

public class PswbMain {

	private static final Logger log = LogManager.getLogger(PswbMain.class);
	
	public static void main(String[] args) {
//		System.out.println("Hello World!!");
		log.info("pswb application started...");

		CourseService service =new CourseService();
		log.info(service.list());
		
	}

}
