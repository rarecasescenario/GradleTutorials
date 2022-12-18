package org.tutorials.concurency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class WorkerThread implements Runnable {

	private String command;
	private static final Logger jsonLogger = LogManager.getLogger("JSON_SYSLOG");
	
	public WorkerThread(String command) {
		super();
		this.command = command;
	}

	
	@Override
	public void run() {
		ThreadContext.put("MODULE", "WorkerThread");
		ThreadContext.put("SYMBOL", "BNS");
		jsonLogger.info("Worker Concurency");
		ThreadContext.clearMap();
	}

	
	private void processCommand() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
