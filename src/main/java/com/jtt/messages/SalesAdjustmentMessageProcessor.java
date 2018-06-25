package com.jtt.messages;

import java.util.regex.*;

import com.jtt.model.*;
import com.jtt.utils.*;

public abstract class SalesAdjustmentMessageProcessor implements MessageProcessor{
	protected MessageProcessor successor = null;
	public SalesAdjustmentMessageProcessor(MessageProcessor successor) {
		this.successor = successor;
	}
	protected abstract String regex();
	protected abstract AdjustmentOperation operation();
	public boolean process(String msg, SalesRegister register) {
		Pattern _p = Pattern.compile(regex());
		Matcher m = _p.matcher(msg);
		if(m.matches()) {
			Product p = new Product(m.group(2));
			Integer scalingFactor = Integer.parseInt(m.group(1));
			register.adjustSales(p, scalingFactor, operation());
			return true;
		}
		else if(successor != null)
			return successor.process(msg, register);
		else return false;
	}
}