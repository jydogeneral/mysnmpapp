package com.jtt.utils;

public class AdjustmentOperations{
	public static AdjustmentOperation multiplying(){
		return (s, f) -> s.getValue() * f ;
	}
	public static AdjustmentOperation adding(){
		return (s, f) -> s.getValue() + f ;
	}
	public static AdjustmentOperation subtracting(){
		return (s, f) -> s.getValue() - f ;
	}
}