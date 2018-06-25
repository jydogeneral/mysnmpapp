package com.jtt.messages;

import com.jtt.model.SalesRegister;

@FunctionalInterface
public interface MessageProcessor{
	boolean process(String msg, SalesRegister register);
}