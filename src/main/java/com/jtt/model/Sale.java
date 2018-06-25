package com.jtt.model;

import java.util.LinkedList;

public class Sale{
	
	private Product product;
	private Integer value;
	private LinkedList<Integer> historicalValues;

	public Sale(Product product, Integer value){
		this.product = product;
		if(!isValidValue(value))
			throw new IllegalArgumentException("Invalid value - cannot be negative");
		else
			this.value = value;
		this.historicalValues = new LinkedList<Integer>();
	}

	public Product getProduct() {
		return this.product;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public void setValue(Integer newValue) {
		if(!isValidValue(newValue))
			throw new IllegalArgumentException("Invalid value - cannot be negative");
		historicalValues.push(value);
		this.value = newValue;
	}
	
	private boolean isValidValue(Integer value){
		return value != null && value >= 0;
	}
	
	public boolean isAdjusted() {
		return historicalValues.size() > 0;
	}
	
	public LinkedList<Integer> getPriceHistory(){
		return new LinkedList<Integer>(historicalValues);
	}
}