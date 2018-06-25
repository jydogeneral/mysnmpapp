package com.jtt.messages;

import com.jtt.utils.*;

public class SubtractOperationMessageProcessor extends SalesAdjustmentMessageProcessor{

	public SubtractOperationMessageProcessor(MessageProcessor successor) {
		super(successor);
	}
	public String regex(){
		return "[Ss]ubtract\\s+(\\d+)p\\s+(\\w+)";
	}
	@Override
	protected AdjustmentOperation operation() {
		return AdjustmentOperations.subtracting();
	}
}