package com.jtt.messages;

import java.util.regex.*;

import com.jtt.model.*;

public class UnitSaleMessageProcessor implements MessageProcessor{
	private MessageProcessor successor = null;
	private Pattern pattern = Pattern.compile("(\\w+)\\s+at\\s+(\\d+)p");

	public UnitSaleMessageProcessor(MessageProcessor successor) {
		this.successor = successor;
	}

	public boolean process(String msg, SalesRegister register) {		
		Matcher m = pattern.matcher(msg);
		if(m.matches()) {
			Product p = new Product(m.group(1));
			Integer value = Integer.parseInt(m.group(2));
			try{
				Sale saleItem = new Sale(p, value);
				register.recordSale(saleItem);
				return true;
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
				return false;
			}			
		}
		else if(successor != null)
			return successor.process(msg, register);
		else return false;
	}
}