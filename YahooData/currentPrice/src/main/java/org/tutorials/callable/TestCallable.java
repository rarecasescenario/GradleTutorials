package org.tutorials.callable;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
/**
 * https://www.youtube.com/watch?v=ysP07P_B88w&ab_channel=KKJavaTutorials
 */
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.tutorials.HttpsClientUtil;
import org.tutorials.SymbolCurrentState;

public class TestCallable {

	private HttpsClientUtil dude = new HttpsClientUtil();
	private static ArrayBlockingQueue<String> symbolQueue = null;
	private CopyOnWriteArrayList<SymbolCurrentState> csl = new CopyOnWriteArrayList<>();


	private List<Callable<SymbolCurrentState>> getTasks(List<String> workList) {
		List<Callable<SymbolCurrentState>> r = new ArrayList<Callable<SymbolCurrentState>>();
			
		for(int i = 0; i < workList.size(); i++) {
			
			String symbol = workList.get(i);
			
			Callable<SymbolCurrentState> task = new Callable<SymbolCurrentState>() {

				@Override
				public SymbolCurrentState call() throws InterruptedException {
					SymbolCurrentState r = null;
					//r = getQuotes(symbol);
					r = getSymbolData(symbol);
					return r;
				}
			};
			r.add(task);
		}
		return r;
	}	
	

	private SymbolCurrentState getSymbolData(String symbol) {
		HttpsClientUtil dude = new HttpsClientUtil();
		SymbolCurrentState r = null;
		
		try {
			InputStream yahooDataPage = dude.getYahooDataPage(symbol);
			try {
				r = dude.getSymbolData(yahooDataPage, symbol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//System.out.println("Price: " + r.getPrice() +  " Prev. Close: " + r.getPreviousClose() + " Percent changed: " + r.getChangedPercent());
			yahooDataPage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return r;	
	}
	
	
	private SymbolCurrentState getQuotes(String symbol) {

//		String symbol = symbolQueue.poll();
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

		return new SymbolCurrentState(symbol, dPrice, dPercent, null);
	}
	
	// https://www.baeldung.com/java-executor-service-tutorial
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		TestCallable tc = new TestCallable();

		List<String> workList = HttpsClientUtil.getSymbolList();
		System.out.println("Get Tasks: List size is: " + workList.size());
		
		symbolQueue = new ArrayBlockingQueue<>(workList.size());
		symbolQueue.addAll(workList);
		System.out.println("Sumbol Queue size: " + symbolQueue.size());		
		
//		String symbol = symbolQueue.poll();
		List<Callable<SymbolCurrentState>> callableTasks = tc.getTasks(workList);
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
//		Future<SymbolCurrentState> future = null;
		List<Future<SymbolCurrentState>> futureList = null;
		
		futureList = executor.invokeAll(callableTasks);
		System.out.println("callableTasks.size = " + callableTasks.size());
		System.out.println("futureList.size = " + futureList.size());
		
		for(int y = 0; y < futureList.size(); y++) {
			Future<SymbolCurrentState> future =	futureList.get(y);
			SymbolCurrentState p = future.get();
			//System.out.println("Callable result = " + p.getSymbol() + " " + p.getPrice() + "  PrevClose: " + p.getPreviousClose() + " Changed %: " + p.getChangedPercent());
			System.out.printf("%-8S | %6.2f | %5.2f | %n", p.getSymbol(), p.getPrice(), p.getChangedPercent());
			
		}
		
			System.out.println("It is done!");
			executor.shutdownNow();
	} // End of main
	

	
	
	public static void main0(String[] args) throws InterruptedException, ExecutionException {

		TestCallable tc = new TestCallable();
		List<String> workList = HttpsClientUtil.getSymbolList();
		System.out.println("List size is: " + workList.size());
		
		symbolQueue = new ArrayBlockingQueue<>(workList.size());
		symbolQueue.addAll(workList);
		String symbol = symbolQueue.poll();
		
		Callable<SymbolCurrentState> task1 = new Callable<SymbolCurrentState>() {

			@Override
			public SymbolCurrentState call() throws InterruptedException {
				SymbolCurrentState r = null;
				try {
					r = tc.getQuotes(symbol);
					//r = new SymbolCurrentState("BNS.TO", new BigDecimal("67.05"), new BigDecimal("6.05"));
					TimeUnit.SECONDS.sleep(1);
					return r;
				} catch (InterruptedException e) {
					throw new IllegalStateException("Task interrupted", e);
				}
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<SymbolCurrentState> future = executor.submit(task1);
	//	executor.shutdownNow();
		
		SymbolCurrentState p = future.get();
		System.out.println("Callable result = " + p.getSymbol() + " " + p.getPrice());
		
		if(future.isDone()) {
			System.out.println("It is done!");
			executor.shutdownNow();
		}

		System.out.println("===============  Result ===================");
		tc.csl.forEach(t -> {

			//System.out.println(t.getSymbol() + "   " + t.getPrice() + "  " + t.getChange_percent() + "%");
			System.out.println(t.toString());
		});
		
	} // End of main0
}
