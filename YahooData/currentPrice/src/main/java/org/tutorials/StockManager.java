package org.tutorials;

/**
 * https://stackoverflow.com/questions/29328769/how-can-executorservice-store-result-of-task-i-in-arrayi
 */


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class StockManager {

	private HttpsClientUtil dude = new HttpsClientUtil();
	private ArrayBlockingQueue<String> symbolQueue = null;

	private CopyOnWriteArrayList<SymbolCurrentState> csl = new CopyOnWriteArrayList<>();

	private void processTasks(CopyOnWriteArrayList cs) {

		String symbol = symbolQueue.poll();
		// System.out.println(symbol);

		String httpResult = null;
		try {
			httpResult = dude.getRawPercent(symbol);
			// System.out.println(symbol + " " + httpResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] r = httpResult.split("\\s*,\\s*");
		BigDecimal dPrice = null;
		BigDecimal dPercent = null;
		try {
			dPrice = new BigDecimal(r[0]);
			dPercent = new BigDecimal(r[1]);
		} catch (NumberFormatException nfe) {
			dPrice = new BigDecimal("0");
			dPercent = new BigDecimal("0");
			// System.out.println("ERROR for symbol: " + symbol);
		}

//		SymbolCurrentState scs = new SymbolCurrentState(symbol, dPrice, dPercent);
//		if (dPrice.compareTo(new BigDecimal("0")) == 1) {
//			csl.add(scs);
//			System.out.println(scs.toString());
//		}
//		//System.out.println("csl.size() = " + csl.size());
//		if(csl.size() > 40) {
//			showResult(csl);
//		}
	}

	
	private void showResult(CopyOnWriteArrayList cs) {
		System.out.println("========= SHOW RESULT ===============");
		System.out.println(" cs.size() = " + cs.size());
		cs.forEach(t -> {
			System.out.println(t.toString());
		});
	}
	
	
	public static void main(String[] args) {

		ExecutorService service = null;
//		CyclicBarrier c1 = new CyclicBarrier(6);
		CyclicBarrier c2 = new CyclicBarrier(6);
		
		StockManager manager = new StockManager();

		service = Executors.newFixedThreadPool(80);

		List<String> workList = HttpsClientUtil.getSymbolList();

		manager.symbolQueue = new ArrayBlockingQueue<>(workList.size()); // workList.size()
		manager.symbolQueue.addAll(workList);

		int n = workList.size();
		CyclicBarrier c1 = new CyclicBarrier(n);

		try {
			for (int i = 0; i < n; i++) {
				service.submit(() -> manager.processTasks(manager.csl));
			}
		} finally {
			if (service != null)
				service.shutdown();
		}
		
		//System.out.println("SIZE csl = " + manager.csl.size());
		manager.csl.forEach(t -> {
			System.out.println(t.toString());
		});
		manager.showResult(manager.csl);
	}
}
