package org.tutorials;

import java.math.BigDecimal;

public class SymbolCurrentState {

	private String symbol;
	private BigDecimal price;
	private BigDecimal change_percent;

	public SymbolCurrentState(String symbol, BigDecimal price, BigDecimal change_percent) {
		super();
		this.symbol = symbol;
		this.price = price;
		this.change_percent = change_percent;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getChange_percent() {
		return change_percent;
	}

	public void setChange_percent(BigDecimal change_percent) {
		this.change_percent = change_percent;
	}

	@Override
	public String toString() {
		return "SymbolCurrentState [symbol=" + symbol + ", price=" + price + ", change_percent=" + change_percent + "]";
	}

}
