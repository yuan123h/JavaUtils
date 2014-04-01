package com.test.java;

public class TestCons {
	private static String field;
	public TestCons() {
		
	}
	
	public TestCons(final String field) {
		this();
		this.field = field;
	}
	
	public static void main(String[] args) {
		TestCons testCons = new TestCons("hee");
		System.out.println(TestCons.field);
	}
}
