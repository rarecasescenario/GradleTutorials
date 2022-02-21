package com.individual;

import java.math.BigDecimal;

public class SymbolSummary {

	private String symbol;
	private int buyShares;
	private BigDecimal buyValue;
	private int soldShares;
	private BigDecimal soldValue;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getBuyShares() {
		return buyShares;
	}
	public void setBuyShares(int buyShares) {
		this.buyShares = buyShares;
	}
	public BigDecimal getBuyValue() {
		return buyValue;
	}
	public void setBuyValue(BigDecimal buyValue) {
		this.buyValue = buyValue;
	}
	public int getSoldShares() {
		return soldShares;
	}
	public void setSoldShares(int soldShares) {
		this.soldShares = soldShares;
	}
	public BigDecimal getSoldValue() {
		return soldValue;
	}
	public void setSoldValue(BigDecimal soldValue) {
		this.soldValue = soldValue;
	}
	@Override
	public String toString() {
		return "SymbolSummary [symbol=" + symbol + ", buyShares=" + buyShares + ", buyValue=" + buyValue
				+ ", soldShares=" + soldShares + ", soldValue=" + soldValue + "]";
	}
}
