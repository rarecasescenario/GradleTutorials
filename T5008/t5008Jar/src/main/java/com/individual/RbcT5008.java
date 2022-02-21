package com.individual;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * T5008 from RBC Activities csv files
 * C:\Taxes\2021\T5\
 * Buy-Jan1toDec31-2021.csv
 * Sell-Jan1toDec31-2021.csv
 * Dividends-Jan1toDec312021.csv
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RbcT5008 {

	private static Logger log = LogManager.getLogger(RbcT5008.class);

	public static void main(String[] args) {
		log.info("info message");

		List<Activity> buyActivities = getData("C:\\Taxes\\2021\\T5\\Buy-Jan1toDec31-2021.csv");
		List<Activity> sellActivities = getData("C:\\Taxes\\2021\\T5\\Sell-Jan1toDec31-2021.csv");

		System.out.println("Bought: " + buyActivities.size());
		System.out.println("Sold: " + sellActivities.size());

//		List<Activity> allActivities = Stream.concat(buyActivities.stream(), sellActivities.stream())
//                .collect(Collectors.toList());

		List<SymbolSummary> allActivities = new ArrayList<>();

		buyActivities.forEach(t -> {
			SymbolSummary bs = new SymbolSummary();
			bs.setSymbol(t.getSymbol());
			bs.setBuyShares(t.getQuantity());
			bs.setBuyValue(t.getValue());
			allActivities.add(bs);
		});

		sellActivities.forEach(t -> {
			SymbolSummary ss = new SymbolSummary();
			ss.setSymbol(t.getSymbol());
			ss.setSoldShares(t.getQuantity());
			ss.setSoldValue(t.getValue());
			allActivities.add(ss);
		});

		Collections.sort(allActivities, new Comparator<SymbolSummary>() {
			@Override
			public int compare(SymbolSummary o1, SymbolSummary o2) {
				return o1.getSymbol().compareToIgnoreCase(o2.getSymbol());
			}
		});


		Map<String, List<SymbolSummary>> symbolMapData = allActivities.parallelStream()
				.collect(Collectors.groupingBy(SymbolSummary::getSymbol));



		List<SymbolSummary> totalSummary = new ArrayList<>();
		BigDecimal yearPL = new BigDecimal("0");
		
		for (Map.Entry<String, List<SymbolSummary>> entry : symbolMapData.entrySet()) {
           // System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            
            List<SymbolSummary> symbolRecords = entry.getValue();
            
            int shares = 0;
            int soldShares = 0;
            BigDecimal buyTotal = new BigDecimal("0"); 
            BigDecimal soldTotal = new BigDecimal("0");
          
                        
            for (int i = 0; i < symbolRecords.size(); i++) {
            	
            	//System.out.println(symbolRecords.get(i).toString());
            	
            	if (symbolRecords.get(i).getBuyValue() != null) {
            		shares = shares + symbolRecords.get(i).getBuyShares();
            		buyTotal = buyTotal.add(symbolRecords.get(i).getBuyValue());
				}

            	if (symbolRecords.get(i).getSoldValue() != null) {
            		soldShares = soldShares + symbolRecords.get(i).getSoldShares();
            		soldTotal = soldTotal.add(symbolRecords.get(i).getSoldValue());
				} 
            }

            SymbolSummary symbolTotals = new SymbolSummary();
            
            symbolTotals.setSymbol(entry.getKey());
            symbolTotals.setBuyShares(shares);
            symbolTotals.setBuyValue(buyTotal);
            symbolTotals.setSoldShares(soldShares);
            symbolTotals.setSoldValue(soldTotal);

            totalSummary.add(symbolTotals);
            
            BigDecimal pl = symbolTotals.getSoldValue().add(symbolTotals.getBuyValue());
            yearPL = yearPL.add(pl);
            
            
            System.out.println(symbolTotals.getSymbol() + "  Bought Shares: " + symbolTotals.getBuyShares() + " Sold shares: " + 
            		symbolTotals.getSoldShares() +  " Buy Proceeds: " + symbolTotals.getBuyValue() +  " Sold Proceeds: " + symbolTotals.getSoldValue() +
            		"  P/L: " + pl);
            
        }
		System.out.println("Profit for the year: " + yearPL);
		
		
//		System.out.println("================================  Show Result =========================");
//		totalSummary.forEach(t -> {
//			System.out.println(t);
//		});
		
		
	}

	/**
	 * 
	 * @param buyInputFile i.e. "C:\\Taxes\\2021\\T5\\Buy-Jan1toDec31-2021.csv"
	 * @return
	 */
	private static List<Activity> getData(String buyInputFile) {
		List<Activity> r = new ArrayList<>();

		Path buyFile = Paths.get(buyInputFile);
		log.info(buyFile.getFileName());

		try (BufferedReader br = Files.newBufferedReader(buyFile)) {

			String line = null;

			while ((line = br.readLine()) != null) {

				if (line.contains("\"Date\","))
					continue;

				String[] rawData = line.split("\\s*,\\s*");

				int quatity = Integer.parseInt(rawData[5].replace("\"", ""));
				BigDecimal price = new BigDecimal(rawData[6].replace("\"", ""));
				BigDecimal value = new BigDecimal(rawData[10].replace("\"", ""));

				Activity act = new Activity(rawData[3].replace("\"", ""), quatity, price, value);
				r.add(act);
			}

		} catch (IOException e) {
			log.error("ERROR: " + e.getMessage());
		}
		return r;
	}

}
