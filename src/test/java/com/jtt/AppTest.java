package com.jtt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jtt.App;
import com.jtt.model.*;

import junit.framework.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{

	public void testSaleValueIsNonnegative(){
		Sale s = new Sale(new Product("apple"), 1);
		try{
			s.setValue(-1);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(true);
		}
	}
	
	public void testSummaryStatistics(){
		String testMessages = new StringBuffer().append("apple at 55p;berry at 155p;Add 5p apple;5 sales of apple at 55p;Subtract 9p apple;Multiply 1p apple;").toString();
		List<String> msgList = Arrays.asList(testMessages.split(";"));
		App app = new App();
		msgList.forEach(msg -> app.accept(msg));		
		List<Sale> appleSales = app.getSales().stream().filter(sale -> sale.getProduct().equals(new Product("apple")) ).collect(Collectors.toList());
		int totalSalesQty = appleSales.size();
		int totalSalesValue = appleSales.stream().mapToInt(Sale::getValue).sum();
		assertTrue(totalSalesQty == 6 && totalSalesValue == 281);
	}

	public void testUnitSaleMessageGeneratesOneLineItemInSalesRegister(){
		String message = "apple at 10p";
		App app = new App();
		int preCount = app.getNumberOfSalesEntries();
		app.accept(message);
		assertTrue(app.getNumberOfSalesEntries() == preCount + 1);
	}

	public void testAggregateSaleMessageGeneratesNLineItemsInSalesRegister(){
		String message = "5 sales of apple at 10p";
		App app = new App();
		int preCount =app.getNumberOfSalesEntries();
		app.accept(message);
		assertTrue(app.getNumberOfSalesEntries() == preCount + 5);
	}

	public void testSalesAdjustmentMessageGeneratesNoNewLineItemInSaleRegister(){
		App app = new App();
		String message = "apple at 10p";
		app.accept(message);
		int preCount = app.getNumberOfSalesEntries();
		message = "Add 10p apple";
		app.accept(message);
		assertTrue(app.getNumberOfSalesEntries() == preCount);
	}

	public void testMultiplyAdjustmentOperation(){
		String message = "5 sales of apple at 10p";
		App app = new App();
		app.accept(message);
		message = "Multiply 5p apple";
		app.accept(message);
		List<Sale> adjustedAppleSale = app.getSales().stream().filter(sale -> sale.getProduct().equals(new Product("apple")) ).collect(Collectors.toList());
		assertTrue(adjustedAppleSale.size() == 5 && adjustedAppleSale.stream().allMatch(sale -> sale.getValue() == 50 ));
	}

	public void testAddAdjustmentOperation(){
		String message = "5 sales of apple at 10p";
		App app = new App();
		app.accept(message);
		message = "Add 5p apple";
		app.accept(message);
		List<Sale> adjustedAppleSale = app.getSales().stream().filter(sale -> sale.getProduct().equals(new Product("apple")) ).collect(Collectors.toList());
		assertTrue(adjustedAppleSale.size() == 5 && adjustedAppleSale.stream().allMatch(sale -> sale.getValue() == 15 ));
	}

	public void testSubtractAdjustmentOperation(){
		String message = "5 sales of apple at 10p";
		App app = new App();
		app.accept(message);
		message = "Subtract 5p apple";
		app.accept(message);
		List<Sale> adjustedAppleSale = app.getSales().stream().filter(sale -> sale.getProduct().equals(new Product("apple")) ).collect(Collectors.toList());
		assertTrue(adjustedAppleSale.size() == 5 && adjustedAppleSale.stream().allMatch(sale -> sale.getValue() == 5 ));
	}
}