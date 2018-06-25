package com.jtt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jtt.utils.AdjustmentOperation;

public class SalesRegister{

	private List<Sale> sales = new ArrayList<>();

	public void recordSale(Sale saleItem) {
		sales.add(new Sale(saleItem.getProduct(), saleItem.getValue()));
	}

	public void adjustSales(Product product, Integer value, AdjustmentOperation op) {
		List<Sale> salesByProduct = getSales(product);
		salesByProduct.forEach(sale -> {
			try{
				sale.setValue(op.apply(sale, value));
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			} 
		});
	}

	public List<Sale> getSales(Product product) {
		return sales.stream().filter(sale -> sale.getProduct().equals(product) ).collect(Collectors.toList());
	}

	public List<Sale> getSales() {
		return new ArrayList<Sale>(sales);
	}
}