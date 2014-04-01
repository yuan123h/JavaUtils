package com.test.java;

public class Apple {
	static {
		System.out.println("apple");
	}
	
	public static void test() {
		System.out.println("My name is apple!");
	}
	
	public static void main(String[] args) {
		Apple a = new Apple();
		a.test();
		Apple.test();
		
	}
}
