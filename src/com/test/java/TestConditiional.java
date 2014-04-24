package com.test.java;

import org.junit.Test;

public class TestConditiional {
	@Test
	public void testBasic() {
		String s = "hello";
		StringBuffer sb = new StringBuffer("hello");
		boolean mutable  = true;
		CharSequence cs = mutable ? s : sb;
		System.out.println(cs);
		
		System.out.println(cs.charAt(2));
		
		int i= 5;
		this.doSomething(i);
	}
	
	public static void doSomething (double d) {
		System.out.println("double" + d);
	}
	
	public static void doSomething (Integer i) {
		System.out.println("integer" + i);
	}
}
