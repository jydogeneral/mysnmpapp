package com.jtt.messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.jtt.model.*;

public class AggregateSaleMessageProcessor implements MessageProcessor{
	private MessageProcessor successor = null;
	private Pattern pattern = Pattern.compile("(\\d+)\\s+sales of\\s+(\\w+)\\s+at\\s+(\\d+)[p]");

	public AggregateSaleMessageProcessor(MessageProcessor successor) {
		this.successor = successor;
	}
	public boolean process(String msg, SalesRegister register) {

		Matcher m = pattern.matcher(msg);
		if(m.matches()) {
			Product p = new Product(m.group(2));
			Integer value = Integer.parseInt(m.group(3));
			Integer qty = Integer.parseInt(m.group(1));
			try{
				Sale saleItem = new Sale(p, value);
				IntStream.
							range(0, qty).
								forEach(n -> register.recordSale(saleItem));
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