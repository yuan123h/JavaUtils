package com.test.java;

import org.junit.Test;

public class TestAssert {
	@Test
	public void testBasic(){
		assert 5 < 0 : "wrong ";
		System.out.println(".");
	}
	
	public static void main(String[] args) {
		new TestAssert().testBasic();
	}
}
