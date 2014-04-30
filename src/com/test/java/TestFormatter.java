package com.test.java;

import java.util.Formatter;

import org.junit.Test;

public class TestFormatter {

	@Test
	public void testBasic () {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("Remaining account balance: $%.2f \n", 0.896);
		formatter.format("Remaining account balance: $%.2f" +
				"(Today's total balance : $%<8.2f)\n", 0.896);
		formatter.format("Remaining account balance: $%(,6.2f" +
				"(Today's total balance : $%(,<8.2f)\n", -1000000.896);
		
		System.out.println(sb.toString());
		
		String s = String.format("Remaining account balance: $%.2f \n", 23423.346);
		System.out.println(s);
		System.out.format("Remaining account balance: %d \n", 23423);
		
		System.out.printf("Remaining account balance: %d %s %n", 23423, "元");
	}
	
}
