package org.tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadsLogging {

	private static Logger logger = LogManager.getLogger(ThreadsLogging.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Dude");
		logger.debug("debug message");
		logger.info("info message");
		logger.error("error message");
	}

}
