package com.yahoofinance.processor;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class GetQuotes {

	public static void main(String[] args) throws IOException {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -5); // from 1 year ago
		 
		Stock google = YahooFinance.get("BNS.TO");
		List<HistoricalQuote> data = google.getHistory(from, to, Interval.DAILY);
		
//		data.forEach(t -> { 
//			System.out.println(t.getOpen() + "  " + t.getClose());
//		});
		
		List<HistoricalQuote> downs = data.stream()
				.filter(t -> t.getOpen().compareTo(t.getClose()) == 1)
				.collect(Collectors.toList());
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
	    sdf.setTimeZone(TimeZone.getDefault());  
	   	
	    BigDecimal critical = new BigDecimal("-1.05");
		downs.forEach(t -> {
	//		Calendar cday = t.getDate();
			BigDecimal diff = t.getClose().subtract(t.getOpen());
			if( diff.compareTo(critical) == -1)
			System.out.println(sdf.format(t.getDate().getTime()) + " Open = " + t.getOpen() + " Close = " + t.getClose() + "        Diff: " + t.getClose().subtract(t.getOpen()));
		});
	}
}
