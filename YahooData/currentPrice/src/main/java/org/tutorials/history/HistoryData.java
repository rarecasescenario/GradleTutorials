package org.tutorials.history;

/**
 * https://stackoverflow.com/questions/11106532/get-date-representation-in-seconds
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HistoryData {

	public static void main(String[] args) {
		
		String historyPriceDataUrl = "https://query1.finance.yahoo.com/v7/finance/download/TD.TO?period1=1548547200&period2=1669507200&interval=1d&events=history&includeAdjustedClose=true";
		HistoryData dude = new HistoryData();
		
		try {
			InputStream historyData = dude.getYahooHistoryData(historyPriceDataUrl);
		
			InputStreamReader isr = new InputStreamReader(historyData);
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
			}
			in.close();
			historyData.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public InputStream getYahooHistoryData(String url) throws Exception {
		URL myurl = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
		return con.getInputStream();
	}
	

}
