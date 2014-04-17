package com.marketstocktips.business.data;

public class Trade {

	private int id;
	private String Type;
	private String ScripName;
	private String StockExchange;
	private double TradeAmoount;
	private double StopLoss;
	private String Status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getScripName() {
		return ScripName;
	}
	public void setScripName(String scripName) {
		ScripName = scripName;
	}
	public String getStockExchange() {
		return StockExchange;
	}
	public void setStockExchange(String stockExchange) {
		StockExchange = stockExchange;
	}
	public double getTradeAmoount() {
		return TradeAmoount;
	}
	public void setTradeAmoount(double tradeAmoount) {
		TradeAmoount = tradeAmoount;
	}
	public double getStopLoss() {
		return StopLoss;
	}
	public void setStopLoss(double stopLoss) {
		StopLoss = stopLoss;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
}
