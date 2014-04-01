package com.test.java.reflect;

public class ToBeLoaded implements Load{
	public ToBeLoaded() {
		System.out.println("init!");
	}

	@Override
	public void method1() {
		System.out.println("i have implemented");
	}
}
