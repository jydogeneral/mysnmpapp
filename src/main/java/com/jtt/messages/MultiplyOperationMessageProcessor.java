package com.jtt.messages;

import com.jtt.utils.*;

public class MultiplyOperationMessageProcessor extends SalesAdjustmentMessageProcessor{

	public MultiplyOperationMessageProcessor(MessageProcessor successor) {
		super(successor);
	}
	public String regex(){
		return "[Mm]ultiply\\s+(\\d+)p\\s+(\\w+)";
	}
	@Override
	protected AdjustmentOperation operation() {
		return AdjustmentOperations.multiplying();
	}
}