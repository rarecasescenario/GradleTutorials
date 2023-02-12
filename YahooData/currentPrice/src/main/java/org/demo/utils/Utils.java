package org.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

	
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
