package com.jtt;

import java.util.*;

import com.jtt.messages.*;
import com.jtt.model.*;
import com.jtt.utils.Reports;

public class App {
	
	private SalesRegister register = new SalesRegister();
	
	private MessageProcessor handler = new UnitSaleMessageProcessor(
			new AggregateSaleMessageProcessor( 
					new AddOperationMessageProcessor(
							new SubtractOperationMessageProcessor(
									new MultiplyOperationMessageProcessor(
											new UnknownMessageProcessor()
											)))));
	private int COUNTER = 0;
	
	public void accept(String message){		
		if(handler.process(message, register)){
			System.out.println("message processed");
			COUNTER++;
			logReport();
			logAdjustmentReport();
		}
		else
			System.out.println(message+" could not be processed");
	}
	
	private void logReport(){
		if(COUNTER % 10 == 0)
			Reports.printSalesSummaryReport(register.getSales());
	}

	private void logAdjustmentReport() {
		if(COUNTER == 50){
			System.out.println("Pausing application...");
			Reports.printAdjustmentsReport(register.getSales());
			System.exit(0);
		}
	}
	
	public int getNumberOfSalesEntries(){
		return register.getSales().size();
	}
	
	public List<Sale> getSales(){
		return register.getSales();
	}
	
	public static void main( String[] args ){
		String testMessages = new StringBuffer().append("apple at 55p;orange at 45p;kiwi at 40p;banana at 22p;berry at 90p;5 sales of apple at 55p;10 sales of orange at 45p;15 sales of kiwi at 40p;5 sales of banana at 22p;2 sales of berry at 90p;")
		.append("Add 5p apple;Add 1p orange;Add 2p kiwi;Add 3p banana;Add 6p berry;Subtract 9p apple;Subtract 6p orange;Subtract 1p kiwi;Subtract 5p banana;")
		.append("Subtract 7p berry;Multiply 1p apple;Multiply 5p orange;Multiply 5p kiwi;Multiply 2p banana;Multiply 1p berry;apple at 55p;orange at 45p;kiwi at 40p;banana at 22p;")
		.append("berry at 90p;5 sales of apple at 55p;10 sales of orange at 45p;15 sales of kiwi at 40p;5 sales of banana at 22p;2 sales of berry at 90p;Add 5p apple;Add 1p orange;Add 2p kiwi;Add 3p banana;Add 6p berry;")
		.append("Subtract 9p apple;Subtract 6p orange;Subtract 1p kiwi;Subtract 5p banana;Subtract 7p berry;Multiply 1p apple;Multiply 5p orange;Multiply 5p kiwi;Multiply 2p banana;Multiply 1p berry;Multiply 1p berry;").toString();
		
		List<String> msgList = Arrays.asList(testMessages.split(";"));
		
		App app = new App();
		System.out.println("Sales Notification Message Processing Application");
		msgList.forEach(msg -> app.accept(msg));
		
//		Scanner scanner = new Scanner(System.in);
//		while (true) {
//            System.out.print("> ");
//            String input = scanner.nextLine();
//            if ("quit".equals(input)) {
//                System.out.println("Closing Application...!");
//                System.exit(0);
//            }
//            else 
//            	app.accept(input);
//            System.out.println();
//        }
	}
}