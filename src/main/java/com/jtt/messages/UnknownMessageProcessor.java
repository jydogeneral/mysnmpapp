package com.jtt.messages;

import com.jtt.model.SalesRegister;

public class UnknownMessageProcessor implements MessageProcessor{
	public boolean process(String msg, SalesRegister register) {
		System.out.println("unknown message format");
		return false;
	}
}