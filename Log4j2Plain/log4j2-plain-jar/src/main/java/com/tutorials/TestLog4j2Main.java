package com.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestLog4j2Main {

	private static Logger logger = LogManager.getLogger(TestLog4j2Main.class);
	
	
	public static void main(String[] args) {
		
		logger.debug("debug message");
		logger.info("info message");
		logger.error("error message");

	}
}
