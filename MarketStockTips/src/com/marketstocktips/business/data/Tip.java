package com.marketstocktips.business.data;

import java.util.ArrayList;

public class Tip {
	
	private int TipId;
	private String TradeType;
	private String TipType;
	private String ScripName;
	private String StockExchange;
	private double ScripValue;
	private String Status;
	private ArrayList<Double> Targets = new ArrayList<Double>();
	private ArrayList<Double> StopLosses = new ArrayList<Double>();

	public String getTradeType() {
		return TradeType;
	}

	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}

	public String getTipType() {
		return TipType;
	}

	public void setTipType(String tipType) {
		TipType = tipType;
	}

	public String getScripName() {
		return ScripName;
	}

	public void setScripName(String scripName) {
		ScripName = scripName;
	}

	public double getScripValue() {
		return ScripValue;
	}

	public void setScripValue(double scripValue) {
		ScripValue = scripValue;
	}

	public void setTargets(ArrayList<Double> targets) {
		Targets = targets;
	}

	public void setStopLosses(ArrayList<Double> stopLosses) {
		StopLosses = stopLosses;
	}

	public ArrayList<Double> getTargets() {
		return Targets;
	}

	public ArrayList<Double> getStopLosses() {
		return StopLosses;
	}
	
	public int getTipId() {
		return TipId;
	}

	public void setTipId(int tipId) {
		TipId = tipId;
	}

	public String getStockExchange() {
		return StockExchange;
	}

	public void setStockExchange(String stockExchange) {
		StockExchange = stockExchange;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
