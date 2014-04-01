package com.test.java;

import org.junit.Test;

public class TestTab {
	@Test
	public void testTab(){
		String s = "tt	sdfsd";
		for (byte b : s.getBytes()) {
			if (b == '\t')
			System.out.println(b);
		}
	}
}
