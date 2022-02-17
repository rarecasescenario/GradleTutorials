package org.tutorials;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * <b>Get the Html source from a https url </b>
 */
public class HttpsClientUtil {

	public static void main(String[] args) throws Exception {
		HttpsClientUtil dude = new HttpsClientUtil();
		// System.out.println("BBY = " + dude.getRawPercent("BBY"));

		List<SymbolCurrentState> csl = new ArrayList<>();

		List<String> workList = getSymbolList();

		for (int i = 0; i < workList.size(); i++) {

			String s = workList.get(i);
			String httpResult = null;
			try {
				httpResult = dude.getRawPercent(s);
				System.out.println(s + "   " + httpResult);
			} catch (Exception e) {
				System.out.println("ERROR for symbol: " + s);
			}

			httpResult = dude.getRawPercent(s);

			String[] r = httpResult.split("\\s*,\\s*");
			BigDecimal dPrice = null;
			BigDecimal dPercent = null;
			try {
				dPrice = new BigDecimal(r[0]);
				dPercent = new BigDecimal(r[1]);
			} catch (NumberFormatException nfe) {
				dPrice = new BigDecimal("0");
				dPercent = new BigDecimal("0");
				System.out.println("ERROR for symbol: " + s);
			}

			SymbolCurrentState scs = new SymbolCurrentState(s, dPrice, dPercent);
			if (dPrice.compareTo(new BigDecimal("0")) == 1)
				csl.add(scs);
		}

		System.out.println("===============  Result ===================");
		csl.forEach(t -> {

			//System.out.println(t.getSymbol() + "   " + t.getPrice() + "  " + t.getChange_percent() + "%");
			System.out.println(t.toString());
		});

	}

	private static List<String> getSymbolList() {
		String symbols = "FIS,F";
		symbols = "AAL,AMD,BBY,BL,CC,CP.TO,PTON,AFRM,IAA,MEG.TO,SNAP,CCO.TO,ENB.TO,PYPL,Z,SWN,CPG.TO,OVV,ACB,POW.TO,CNQ.TO,BTO,MEG.TO,AIM,SU.TO,ARX.TO,BB,VET,CNR,ALA.TO,PEY,ERF,PD,ELD,AEM,PSK,CG,ARE,RY.TO,BMO.TO,TD.TO";
		//String symbols = "AAL,ENB.TO,BBY,RY.TO,BMO.TO,TD.TO,MEG.TO,CC,CP.TO,PTON,AFRM,IAA,SE,SPLK";
		//String symbols = "ENB.TO,CP.TO,RY.TO,BMO.TO,TD.TO";
		
		// Will go up on Feb 16, 2022 (FM)
		//String symbols = "FIS,F";
		
		//Today watch list Feb 16, 2022
		//symbols = "AFRM,ZM,F,FIS,PTON,WELL,ENB.TO,SLF.TO,RY.TO,ARX.TO";
		
		// most active
		//String symbols = "AMD,SOFI,RBGLY,AAPL,GT,SPLK,TSLA,F,AMC";
		String symb[] = symbols.split("\\s*,\\s*");

		List<String> symbolList = Arrays.asList(symb);

		return symbolList;
	}

	/*
	 * String httpsURL = "https://finance.yahoo.com/quote/AAL?p=AAL";
	 */
	public String getRawPercent(String symbol) throws Exception {
		String result = "";
		String price = "";
		String httpsURL = "https://finance.yahoo.com/quote/" + symbol + "?p=" + symbol;

		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		String inputLine;

		String rawPercent = null;

		while ((inputLine = in.readLine()) != null) {

			String p2 = "data-symbol=\"" + symbol + "\"";
			String rawResult = null;

			if (inputLine.contains("data-field=\"regularMarketPrice\"") && inputLine.contains(p2)) {

				// System.out.println(inputLine);
				int startValue = inputLine.indexOf("value=\"");
				int endValue = inputLine.indexOf("active=\"\">");
				rawResult = inputLine.substring(startValue, endValue);

				price = makeItDigit(rawResult);

				if ((inputLine.contains("<span class=\"C($negativeColor)\">(")
						|| inputLine.contains("<span class=\"C($positiveColor)\">"))
						&& inputLine.contains("%)</span></fin-streamer>")) {

					// System.out.println("pStart = " + pStart);
					int pEnd = inputLine.indexOf(("%)</span></fin-streamer>"));

					int pStart = pEnd - 6;

					rawPercent = inputLine.substring(pStart, pEnd);
					// System.out.println("Percent: " + rawPercent);

					String percent = makeItDigit(rawPercent);
					// System.out.println("PERCENT: " + percent);

					result = price + ",                    " + percent;
				}

			}

		}
		in.close();

		return result;
	}

	/*
	 * String httpsURL = "https://finance.yahoo.com/quote/AAL?p=AAL";
	 */
	public String getRawPrice(String symbol) throws Exception {
		String price = "";
		String httpsURL = "https://finance.yahoo.com/quote/" + symbol + "?p=" + symbol;

		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		String inputLine;

		while ((inputLine = in.readLine()) != null) {

			String p2 = "data-symbol=\"" + symbol + "\"";
			String rawResult = null;

			if (inputLine.contains("data-field=\"regularMarketPrice\"") && inputLine.contains(p2)) {

				int startValue = inputLine.indexOf("value=\"");
				int endValue = inputLine.indexOf("active=\"\">");
				rawResult = inputLine.substring(startValue, endValue);

				price = makeItDigit(rawResult);
			}

		}
		in.close();

		return price;
	}

	/**
	 * Scans string and extracts digits in order to build a number representation of
	 * the value
	 * 
	 * @param p Piece of raw string containing data
	 * @return Digital representation of the value
	 */
	public synchronized String makeItDigit(String p) {
		String result = "";
		boolean isDigitSeen = false;
		int e;
		for (e = 0; e < p.length(); e++) {
			switch (p.charAt(e)) {
			case ',':
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case ':':
			case '-':
			case '+':
			case '.':
				isDigitSeen = true;
				result = result + p.charAt(e);
				break;
			}
		}
		if (!isDigitSeen) {
			return "0";
		}
		return result;
	}

}