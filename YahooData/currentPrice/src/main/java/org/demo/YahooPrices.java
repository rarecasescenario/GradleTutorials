package org.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class YahooPrices {

	public static void main(String[] args) throws IOException, ParseException {

		final String SYM = "ENB.TO";
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);

		java.util.Date period1 = datechange(cal);
		cal1.set(Calendar.YEAR, 2023);
		cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal1.set(Calendar.DAY_OF_MONTH, 10);
		java.util.Date period2 = datechange(cal1);

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
			System.out.println(line);
			line = buf.readLine();
		}
	}

	public static java.util.Date datechange(Calendar cal) throws ParseException {
		java.util.Date dateOne = cal.getTime();
		// boolean date1904 = true;
		// double ans =DateUtil.getExcelDate(cal,date1904);

		String a = dateOne.toString();
		String b[] = a.split(" ");
		String c = b[1] + " " + b[2] + " " + b[5];
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTime(sdf.parse(c));
		dateOne = cal.getTime();
		sdf.format(dateOne);
		return dateOne;
	}
}