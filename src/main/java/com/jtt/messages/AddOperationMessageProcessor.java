package com.jtt.messages;

import com.jtt.utils.*;

public class AddOperationMessageProcessor extends SalesAdjustmentMessageProcessor{

	public AddOperationMessageProcessor(MessageProcessor successor) {
		super(successor);
	}
	public String regex(){
		return "[Aa]dd\\s+(\\d+)p\\s+(\\w+)";
	}
	@Override
	protected AdjustmentOperation operation() {
		return AdjustmentOperations.adding();
	}
}