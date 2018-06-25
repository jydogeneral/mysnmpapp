package com.jtt.utils;

import static java.util.stream.Collectors.*;
import java.util.*;

import com.jtt.model.*;

public class Reports {

	public static void printSalesSummaryReport(List<Sale> sales) {
		System.out.println("SALES REPORT");
		System.out.println("=============");
		System.out.println("Name   Qty   Value(Total)");
		System.out.println("====================");
		Map<Product, List<Sale>> map = sales.stream().collect(groupingBy(Sale::getProduct));
		map.forEach((product, list) -> 
								System.out.println(product.getName()+"	"+list.size()+"	"+list.stream().mapToInt(Sale::getValue).sum()));
		System.out.println("====================");
	}

	public static void printAdjustmentsReport(List<Sale> sales) {
		System.out.println("ADJUSTMENT REPORT");
		System.out.println("==============================");
		System.out.println("Name	Value(Current)	Value(History)");
		System.out.println("==============================");
		List<String> report = sales.stream().map(
										   s -> s.getProduct().getName() + "	"+s.getValue()+"	"+s.getPriceHistory()).collect(toList());
		report.forEach(System.out::println);
		System.out.println("==============================");
	}
}
