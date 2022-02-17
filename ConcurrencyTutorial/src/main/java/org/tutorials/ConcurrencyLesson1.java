package org.tutorials;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class ConcurrencyLesson1 {

	private static final Logger logger = LogManager.getLogger(ConcurrencyLesson1.class);

	public static void main(String[] args) {
		// Add context information
		ThreadContext.put("id", UUID.randomUUID().toString());
		ThreadContext.put("business", "Concurrency and MultiTreading");
		logger.info("Single Executor Service started...");

		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			logger.info("begin");

			service.execute(() -> System.out.println("Printing Zoo inventory"));

			logger.debug("Debug Message Logged !!");
			logger.info("Info Message Logged !!");
			logger.debug("Another Debug Message !!");
		} finally {
			if (service != null)
				service.shutdown();
		}
		// Clear the map
		ThreadContext.clearMap();

		logger.debug("Thread Context Cleaned up !!");
		logger.debug("Log message with no context information !!");
		logger.info("end");

	}
}
