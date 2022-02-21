package com.individual;

import java.math.BigDecimal;

public class Activity {

	
	private String activity;
	private String symbol;
	private String symbolDescription;
	private int quantity;
	private BigDecimal price;
	private String settlementDate;
	private String account;
	private BigDecimal value;
	private String currency;
	private String description;
	
	public Activity(String symbol, int quantity, BigDecimal price, BigDecimal value) {
		super();
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		this.value = value;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbolDescription() {
		return symbolDescription;
	}

	public void setSymbolDescription(String symbolDescription) {
		this.symbolDescription = symbolDescription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Activity [activity=" + activity + ", symbol=" + symbol + ", symbolDescription=" + symbolDescription
				+ ", quantity=" + quantity + ", price=" + price + ", settlementDate=" + settlementDate + ", account="
				+ account + ", value=" + value + ", currency=" + currency + ", description=" + description + "]";
	}

}
