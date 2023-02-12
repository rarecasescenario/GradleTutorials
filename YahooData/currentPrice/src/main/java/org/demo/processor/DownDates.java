package org.demo.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.demo.pojo.YahooHistoryData;
import org.demo.utils.Utils;

public class DownDates {

	
	
	public static void main(String[] args) throws IOException, ParseException{
		
		List<YahooHistoryData> dd = new ArrayList();
		
		final String SYM = "ENB.TO";
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 15);

		java.util.Date period1 = Utils.datechange(cal);
		cal1.set(Calendar.YEAR, 2023);
		cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal1.set(Calendar.DAY_OF_MONTH, 10);
		java.util.Date period2 = Utils.datechange(cal1);

		String interval = "1d";

		long strDate = (period1.getTime());
		strDate = strDate / 1000;
		long strDate1 = (period2.getTime());
		strDate1 = strDate1 / 1000;
		// System.out.println(strDate+" "+strDate1+" ans");
		String link = "https://query1.finance.yahoo.com/v7/finance/download/" + SYM + "?period1=" + strDate
				+ "&period2=" + strDate1 + "&interval=" + interval + "&events=history";

		URL url = new URL(link);
		URLConnection urlConn = url.openConnection();
		InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buf = new BufferedReader(inStream);

		String line = buf.readLine();
		while (line != null) {
			line = buf.readLine();
			System.out.println(line);
			
			if(!line.isEmpty() && line.contains(",")) {
			String[] data = line.split(",",7);
			System.out.println("Date = " + data[0] + " Open = " + data[1]);
			
//			LocalDate date = LocalDate.parse(data[0]);
//			Double open = Double.valueOf(data[1]);
//			Double high = Double.valueOf(data[2]);
//			Double low = Double.valueOf(data[3]);
//			Double close = Double.valueOf(data[4]);
//			
//			YahooHistoryData inDate = new YahooHistoryData();
//			inDate.setDate(date);
//			inDate.setOpen(open);
//			inDate.setHigh(high);
//			inDate.setLow(low);
//			inDate.setClose(close);
//			
//			dd.add(inDate);
			}
		}

	}

}
