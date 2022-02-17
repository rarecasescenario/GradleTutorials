package org.tutorials;

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.text.GapContent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class SleepRunnable {

	private static final Logger logger = LogManager.getLogger(SleepRunnable.class);
	private static AtomicInteger g = new AtomicInteger(0);
	private volatile static boolean running = true;

	public void terminate() {
		running = false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		 int p = Runtime.getRuntime().availableProcessors();
		 System.out.println("Number of available processor is: " + p);
		
		Runnable r = () -> {

			ThreadContext.put("id", "Runnable");
			ThreadContext.put("business", "Concurrency and MultiTreading");

			Thread ct = Thread.currentThread();
			while (!ct.isInterrupted()) {
				logger.info("thread works: " + g);
				try {

					g.incrementAndGet();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
			
					return;
				}
			}
		};

		Thread t = new Thread(r);

		t.start();

		ThreadContext.clearMap();
		logger.info("end");
	}

}
