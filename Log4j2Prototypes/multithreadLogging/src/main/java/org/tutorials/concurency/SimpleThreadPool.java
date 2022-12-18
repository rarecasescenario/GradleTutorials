package org.tutorials.concurency;

/**
 * https://sematext.com/blog/log4j2-tutorial/
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleThreadPool {

	private static final Logger logger = LogManager.getLogger(SimpleThreadPool.class);
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		logger.trace("Trace message $1");
		logger.debug("Debug message $2");
		logger.info("Info message $3");
		logger.warn("Warn message $4");
		logger.error("Error message $5");
		
		for( int i = 0; i < 10; i++) {
			logger.info("Simple For Loop : " + i);
			Runnable worker = new WorkerThread("" + i);
			executor.execute(worker);
		}
		executor.shutdown();
		while(!executor.isTerminated()) {
		}
		logger.info("SimpleThreadPool Executor shutted down.");

	}

}
